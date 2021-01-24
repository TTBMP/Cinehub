package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.datamapper.*;
import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.repository.*;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.usecase.Request;

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
                                      ProjectionRepository projectionRepository) {
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
    }

    @Override
    public void sendEmail(SendEmailRequest request) {
        try {
            Request.validate(request);
            //Prender i dati dell'utente dall'id
            User user = new User("Ivan", "palm@5934.cos", new CreditCard("22/24", 354, "4242424242424242", "5496"));
            emailService.sendMail(user.getEmail(), "Hello Member", "Thanks for joining us.");
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
            User user = new User("Ivan", "palm@5934.cos", new CreditCard("22/24", 354, "4242424242424242", "5496"));
            Ticket ticket = TicketDataMapper.mapToEntity(request.getTicket());
            Projection projection = ProjectionDataMapper.mapToEntity(request.getProjection());
            Integer index = request.getIndex();
            if (paymentService.pay(user, ticket)) {
                ticket.setState(true);
                projection.addTicket(ticket);
                projection.getHall().getSeatList().get(index).setState(false);
                user.addTicket(ticket);
                return true;
            }
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
    public void getListMovie() {
        List<Movie> movieList = movieRepository.getAllMovieByApi(movieApiService);
        buyTicketPresenter.presentMovieApiList(new GetListMovieResponse(MovieDataMapper.mapToDtoList(movieList)));
    }

    @Override
    public void getTicketBySeats(GetTicketBySeatsRequest request) {
        try {
            Request.validate(request);
            List<Seat> seats = SeatDataMapper.mapToEntityList(request.getSeats());
            Integer pos = request.getPos();
            String position = request.getPosition();
            Seat selectedSeats = seats.get(pos);
            Ticket ticket = new Ticket(selectedSeats.getPrice());
            ticket.setPosition(position);
            buyTicketPresenter.setSelectedTicket(new GetTicketBySeatsResponse(TicketDataMapper.mapToDto(ticket)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTicketBySeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTicketBySeats(request);
        }


    }


    @Override
    public void getListCinema(GetListCinemaRequest request) {
        try {
            Request.validate(request);
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            List<Projection> projectionList = projectionRepository.getProjectionByMovie(movie);
            List<Cinema> cinemaList = cinemaRepository.getCinemaByProjection(projectionList);
            buyTicketPresenter.presentCinemaList(new GetListCinemaResponse(CinemaDataMapper.mapToDtoList(cinemaList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetListCinemaNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetListCinema(request);
        }


    }

    @Override
    public void getTimeOfProjection(GetTimeOfProjecitonRequest request) {
        try {
            Request.validate(request);
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            List<String> timeList = new ArrayList<>();
            if (cinema != null) {
                timeList = projectionRepository.getTimeOfProjectionByMovieEndCinema(movie, cinema);
            }
            buyTicketPresenter.presentTimeList(timeList);//Non serve una reponse per una lista di stringhe
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTimeOfProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTimeOfProjection(request);
        }

    }

    @Override
    public void confirmSeatsRandom() {
        buyTicketPresenter.confirmSeatsRandom();
    }

    @Override
    public void confirmSeatsSpecific() {
        buyTicketPresenter.confirmSeatsSpecific();
    }

    @Override
    public void setProjection(SetProjectionRequest request) {
        try {
            Request.validate(request);
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            String time = request.getTime();
            Projection projection = projectionRepository.getTimeOfProjectionByMovieEndCinemaEndTime(movie, cinema, time);
            buyTicketPresenter.presentProjection(new SetProjectionResponse(ProjectionDataMapper.mapToDto(projection)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentSetProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidSetProjection(request);
        }
    }

    @Override
    public void getNumberOfSeats(GetNumberOfSeatsRequest request) {
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