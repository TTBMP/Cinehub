package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.*;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PayServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketFoldingArmchair;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketHeatedArmchair;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

import java.io.IOException;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketController implements BuyTicketUseCase {

    private final BuyTicketPresenter buyTicketPresenter;

    private final PaymentService paymentService;
    private final EmailService emailService;
    private final SecurityService securityService;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ProjectionRepository projectionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public BuyTicketController(ServiceLocator serviceLocator, BuyTicketPresenter buyTicketPresenter) {
        this.buyTicketPresenter = buyTicketPresenter;
        this.paymentService = serviceLocator.getService(PaymentService.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
        this.movieRepository = serviceLocator.getService(MovieRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
    }

    @Override
    public void pay(PaymentRequest request) {
        try {
            Request.validate(request);
            var user = userRepository.getUser(securityService.authenticate("").getId());
            var ticket = TicketDataMapper.mapToEntity(request.getTicket());
            paymentService.pay(new PayServiceRequest(
                    user.getEmail(),
                    user.getName(),
                    user.getCreditCard().getNumber(),
                    ticket.getPrice()
            ));
            ticket.setOwner(user);
            ticketRepository.saveTicket(ticket, ProjectionDataMapper.mapToEntity(request.getProjection()));
            emailService.sendMail(new EmailServiceRequest(user.getEmail(), "Payment receipt"));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentPayNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidPay(request);
        } catch (PaymentServiceException e) {
            buyTicketPresenter.presentErrorByStripe(e);
        } catch (SecurityException e) {
            buyTicketPresenter.presentAuthenticationError();
        }
    }

    @Override
    public void getListMovie(GetListMovieRequest request) {
        try {
            Request.validate(request);
            var localDate = request.getDate().toString();
            var movieList = movieRepository.getMovieList(localDate);
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
            var movie = movieRepository.getMovieById(request.getMovieId());
            var date = request.getData();
            var cinemaList = cinemaRepository.getListCinema(movie, date);
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
            var seats = SeatDataMapper.mapToEntityList(request.getSeatDtoList());
            var pos = request.getPos();
            var seat = seats.get(pos);
            var user = userRepository.getUser(securityService.authenticate("").getId());
            /*DECORATOR PATTERN GOF*/
            var ticket = new Ticket(0, seat.getPrice(), user, seat);
            if (Boolean.TRUE.equals(request.getHeatedArmchairOption())) {
                ticket = new TicketSkipLine(ticket);
                ticket.setPrice(ticket.getPrice());
            }
            if (Boolean.TRUE.equals(request.getFoldingArmchairOption())) {
                ticket = new TicketFoldingArmchair(ticket);
                ticket.setPrice(ticket.getPrice());
            }
            if (Boolean.TRUE.equals(request.getSkipLineRadioOption())) {
                ticket = new TicketHeatedArmchair(ticket);
                ticket.setPrice(ticket.getPrice());
            }
            /*-----------------------------------------*/
            buyTicketPresenter.setSelectedTicket(new GetTicketBySeatsResponse(TicketDataMapper.mapToDto(ticket)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTicketBySeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTicketBySeats(request);
        } catch (SecurityException e) {
            buyTicketPresenter.presentAuthenticationError();
        }
    }

    /*To find the times of the screenings given a film, a cinema and a date*/
    @Override
    public void getProjectionList(GetProjectionListRequest request) {
        try {
            Request.validate(request);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            var movie = movieRepository.getMovieById(request.getMovieId());
            var date = request.getLocalDate();
            var projectionList = projectionRepository.getProjectionList(cinema, movie, date);
            buyTicketPresenter.presentProjectionList(
                    new GetProjectionListResponse(ProjectionDataMapper.mapToDtoList(projectionList))
            );
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTimeOfProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTimeOfProjection(request);
        }
    }


    @Override
    public void getProjection(GetProjectionRequest request) {
        var projection = projectionRepository.getProjection(
                request.getLocalDate(),
                request.getStartTime(),
                request.getHallId()
        );
        buyTicketPresenter.presentProjection(new GetProjectionResponse(ProjectionDataMapper.mapToDto(projection)));
    }

    @Override
    public void getCinema(GetCinemaRequest request) {
        try {
            Request.validate(request);
            var cinema = cinemaRepository.getCinema(
                    ProjectionDataMapper.mapToEntity(request.getProjectionDto())
            );
            buyTicketPresenter.presentCinema(new GetCinemaResponse(CinemaDataMapper.mapToDto(cinema)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetListCinemaNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetCinema(request);
        }
    }


    @Override
    public void getListOfSeat(GetNumberOfSeatsRequest request) {
        try {
            Request.validate(request);
            var projection = ProjectionDataMapper.mapToEntity(request.getProjectionDto());
            var hall = projection.getHall();
            var seatList = hall.getSeatList();
            buyTicketPresenter.presentSeatList(new GetNumberOfSeatsResponse(SeatDataMapper.mapToDtoList(seatList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetNumberOfSeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetNumberOfSeats(request);
        }
    }

}
