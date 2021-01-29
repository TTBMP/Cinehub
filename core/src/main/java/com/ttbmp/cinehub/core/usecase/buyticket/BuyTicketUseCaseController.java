package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.datamapper.*;
import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketFoldingArmchair;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketHeatedArmchair;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketSkipLine;
import com.ttbmp.cinehub.core.repository.*;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;
import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;
import com.ttbmp.cinehub.core.usecase.buyticket.response.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketUseCaseController implements BuyTicketUseCase {

    PaymentService paymentService;
    EmailService emailService;
    MovieApiService movieApiService;
    BuyTicketPresenter buyTicketPresenter;
    MovieRepository movieRepository;
    CinemaRepository cinemaRepository;
    TimeRepository timeRepository;
    SeatRepository seatRepository;
    AuthenticationService authenticationService;
    HallRepository hallRepository;
    ProjectionRepository projectionRepository;
    UserRepository userRepository;


    public BuyTicketUseCaseController(PaymentService paymentService,
                                      EmailService emailService,
                                      BuyTicketPresenter buyTicketPresenter,
                                      MovieApiService movieApiService,
                                      MovieRepository movieRepository,
                                      CinemaRepository cinemaRepository,
                                      TimeRepository timeRepository,
                                      SeatRepository seatRepository,
                                      AuthenticationService authenticationService,
                                      HallRepository hallRepository,
                                      ProjectionRepository projectionRepository,
                                      UserRepository userRepository) {
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.buyTicketPresenter = buyTicketPresenter;
        this.movieApiService = movieApiService;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.timeRepository = timeRepository;
        this.seatRepository = seatRepository;
        this.authenticationService = authenticationService;
        this.hallRepository = hallRepository;
        this.projectionRepository = projectionRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void sendEmail(SendEmailRequest request) {
        try {
            Request.validate(request);
            User user = userRepository.getUser(0).getValue();
            //Prendere i dati dell'utente dall'id
            emailService.sendMail(new EmailServiceRequest(user.getEmail(), "Hello Member"));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentSendEmailNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidSendEmail(request);
        }
    }


    @Override
    public boolean pay(PayRequest request) {
        try {
            Request.validate(request);
            int userId = authenticationService.sigIn();
            User user = userRepository.getUser(userId).getValue();
            TicketAbstract ticketAbstract = TicketDataMapper.mapToEntity(request.getTicket());
            Projection projection = ProjectionDataMapper.mapToEntity(request.getProjection());
            Integer index = request.getIndex();
            if (paymentService.pay(new PayServiceRequest(user.getEmail(), user.getName(), user.getCard().getNumber(), ticketAbstract.getPrice()))) {
                ticketAbstract.setState(true);
                projection.addTicket(ticketAbstract);
                projection.getHall().getSeatList().get(index).setState(false);
                user.addTicket(ticketAbstract);
                return true;
            }
            buyTicketPresenter.presentErrorByStripe(paymentService.getError());
            return false;
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentPayNullRequest();
            return false;
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidPay(request);
            return false;
        }
    }


    @Override
    public void getListMovie(String localDate) {
        List<Movie> movieListByData = new ArrayList<>();
        try {
            List<Movie> movieList = MovieDataMapper.mapToEntityList(movieRepository.getAllMovie(movieApiService));
            List<Projection> projectionList = projectionRepository.getAllProjection();
            for (Projection projection : projectionList) {
                for (Movie movie : movieList) {
                    if (projection.getMovie().getName().equals(movie.getName()) && projection.getDate().equals(localDate)) {
                        movieListByData.add(movie);
                    }
                }
            }
            for (int i = 0; i < movieListByData.size(); i++) {
                for (int j = i + 1; j < movieListByData.size(); j++) {
                    if (movieListByData.get(i).getName().equals(movieListByData.get(j).getName())) {
                        movieListByData.remove(j);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            buyTicketPresenter.presentGetListMovie();
        }
        buyTicketPresenter.presentMovieApiList(new GetListMovieResponse(MovieDataMapper.mapToDtoList(movieListByData)));
    }


    @Override
    public void getListCinema(GetListCinemaRequest request) {
        try {
            Request.validate(request);

            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            String date = request.getData();

            List<Projection> projectionList = projectionRepository.getAllProjection();
            List<Projection> projectionListByMovie = new ArrayList<>();

            for (Projection projection : projectionList) {
                if (projection.getDate().equals(date) && projection.getMovie().getName().equals(movie.getName())) {
                    projectionListByMovie.add(projection);
                }
            }
            List<Cinema> cinemaList = new ArrayList<>();
            for (Projection projection : projectionListByMovie) {
                cinemaList.add(projection.getCinema());
            }
            for (int i = 0; i < cinemaList.size(); i++) {
                for (int j = i + 1; j < cinemaList.size(); j++) {
                    if (cinemaList.get(i).getName().equals(cinemaList.get(j).getName())) {
                        cinemaList.remove(j);
                        break;
                    }
                }
            }
            buyTicketPresenter.presentCinemaList(new GetListCinemaResponse(CinemaDataMapper.mapToDtoList(cinemaList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetListCinemaNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetListCinema(request);
        }


    }

    @Override
    public void createTicket(GetTicketBySeatsRequest request) {
        try {
            Request.validate(request);
            List<Seat> seats = SeatDataMapper.mapToEntityList(request.getSeatDtoList());
            Integer pos = request.getPos();
            String position = request.getPosition();
            Seat selectedSeats = seats.get(pos);

            /*DECORATOR PATTERN GOF*/
            TicketAbstract ticketAbstract = new Ticket(selectedSeats.getPrice());
            ticketAbstract.increasePrice();
            if (Boolean.TRUE.equals(request.getHeatedArmchairOption())) {
                ticketAbstract = new TicketSkipLine(ticketAbstract);
                ticketAbstract.setPrice(ticketAbstract.increasePrice());
            }
            if (Boolean.TRUE.equals(request.getFoldingArmchairOption())) {
                ticketAbstract = new TicketFoldingArmchair(ticketAbstract);
                ticketAbstract.setPrice(ticketAbstract.increasePrice());
            }
            if (Boolean.TRUE.equals(request.getSkipLineRadioOption())) {
                ticketAbstract = new TicketHeatedArmchair(ticketAbstract);
                ticketAbstract.setPrice(ticketAbstract.increasePrice());
            }
            /*-----------------------------------------*/

            ticketAbstract.setPosition(position);
            buyTicketPresenter.setSelectedTicket(new GetTicketBySeatsResponse(TicketDataMapper.mapToDto(ticketAbstract)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTicketBySeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTicketBySeats(request);
        }


    }

    @Override
    public void getTimeOfProjection(GetTimeOfProjecitonRequest request) {
        try {
            Request.validate(request);
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            String date = request.getLocalDate();
            List<String> timeList = new ArrayList<>();
            if (cinema != null) {
                List<Projection> projectionList = projectionRepository.getAllProjection();
                for (Projection projection : projectionList) {
                    if (projection.getCinema().getName().equals(cinema.getName()) &&
                            projection.getMovie().getName().equals(movie.getName()) &&
                            projection.getDate().equals(date)) {
                        timeList.add(projection.getStartTime());
                    }
                }
            }
            buyTicketPresenter.presentTimeList(timeList);//Non serve una reponse per una lista di stringhe
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTimeOfProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTimeOfProjection(request);
        }

    }


    @Override
    public void getProjection(SetSelectedProjectionRequest request) {
        try {
            Request.validate(request);
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            String date = request.getDate();
            String time = request.getTime();
            List<Projection> projectionList = projectionRepository.getAllProjection();
            for (Projection projection : projectionList) {
                if (projection.getCinema().getName().equals(cinema.getName()) &&
                        projection.getMovie().getName().equals(movie.getName()) &&
                        projection.getStartTime().equals(time) &&
                        projection.getDate().equals(date)) {
                    buyTicketPresenter.presentProjection(new SetProjectionResponse(ProjectionDataMapper.mapToDto(projection)));
                }
            }
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentSetProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidSetProjection(request);
        }
    }

    @Override
    public void getListOfSeatsByProjection(GetNumberOfSeatsRequest request) {
        try {
            Request.validate(request);
            Projection projection = ProjectionDataMapper.mapToEntity(request.getProjectionDto());
            Hall hall = projection.getHall();
            List<Seat> seatList = hall.getSeatList();
            buyTicketPresenter.presentSeatList(new GetNumberOfSeatsResponse(SeatDataMapper.mapToDtoList(seatList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetNumberOfSeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetNumberOfSeats(request);
        }

    }
}
