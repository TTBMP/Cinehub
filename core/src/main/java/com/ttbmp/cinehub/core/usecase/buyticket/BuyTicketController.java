package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.datamapper.*;
import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketFoldingArmchair;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketHeatedArmchair;
import com.ttbmp.cinehub.core.entity.ticket.decorator.TicketSkipLine;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.MovieRepository;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;
import com.ttbmp.cinehub.core.repository.UserRepository;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.core.service.payment.PaymentException;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;
import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;
import com.ttbmp.cinehub.core.usecase.buyticket.response.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketController implements BuyTicketUseCase {

    private final PaymentService paymentService;
    private final EmailService emailService;
    private final BuyTicketPresenter buyTicketPresenter;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final AuthenticationService authenticationService;
    private final ProjectionRepository projectionRepository;
    private final UserRepository userRepository;


    public BuyTicketController(PaymentService paymentService,
                               EmailService emailService,
                               BuyTicketPresenter buyTicketPresenter,
                               MovieRepository movieRepository,
                               CinemaRepository cinemaRepository,
                               AuthenticationService authenticationService,
                               ProjectionRepository projectionRepository,
                               UserRepository userRepository) {
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.buyTicketPresenter = buyTicketPresenter;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.authenticationService = authenticationService;
        this.projectionRepository = projectionRepository;
        this.userRepository = userRepository;

    }


    @Override
    public boolean pay(PayRequest request) {
        try {
            Request.validate(request);
            User user = userRepository.getUser(authenticationService.sigIn()).getValue();
            Ticket ticket = TicketDataMapper.mapToEntity(request.getTicket());
            Projection projection = ProjectionDataMapper.mapToEntity(request.getProjection());
            Integer index = request.getIndex();
            paymentService.pay(new PayServiceRequest(
                    user.getEmail(),
                    user.getName(),
                    user.getCard().getNumber(),
                    ticket.getPrice()
            ));
            ticket.setState(true);
            projection.addTicket(ticket);
            projection.getHall().getSeatList().get(index).setState(false);
            user.addTicket(ticket);
            emailService.sendMail(new EmailServiceRequest(
                    user.getEmail(),
                    "Payment receipt"
            ));
            return true;
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentPayNullRequest();
            return false;
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidPay(request);
            return false;
        } catch (PaymentException e) {
            buyTicketPresenter.presentErrorByStripe(e);
            return false;
        }
    }

    @Override
    public void getListMovie(GetListMovieRequest request) {
        try {
            Request.validate(request);
            String localDate = request.getDate().toString();
            List<Movie> movieList = movieRepository.getMovieList(localDate);//I recover them movies from the bee service
            buyTicketPresenter.presentMovieApiList(new GetListMovieResponse(MovieDataMapper.mapToDtoList(movieList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetListMovieNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetListMovie(request);
        } catch (IOException e) {
            buyTicketPresenter.presentGetListMovieError();
        }
    }


    @Override
    public void getListCinema(GetListCinemaRequest request) {
        try {
            Request.validate(request);
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            String date = request.getData();
            List<Cinema> cinemaList = cinemaRepository.getListCinema(movie, date);
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

    /*To find the times of the screenings given a film, a cinema and a date*/
    @Override
    public void getProjectionList(GetTimeOfProjectionRequest request) {
        try {
            Request.validate(request);
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            String date = request.getLocalDate();
            List<Projection> projectionList = projectionRepository.getProjectionList(
                    CinemaDataMapper.mapToDto(cinema),
                    MovieDataMapper.mapToDto(movie),
                    date);
            buyTicketPresenter.presentProjectionList(
                    new ProjectionListResponse(ProjectionDataMapper.mapToDtoList(projectionList)));//Lista delle possiobili proiezioni da scegliere
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTimeOfProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTimeOfProjection(request);
        }
    }


    @Override
    public void getListOfSeat(GetNumberOfSeatsRequest request) {
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
