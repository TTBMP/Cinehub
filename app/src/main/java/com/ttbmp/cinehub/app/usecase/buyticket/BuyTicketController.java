package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.*;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PayServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketMagicBox;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketOpenBar;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketController implements BuyTicketUseCase {

    private final BuyTicketPresenter presenter;
    private final PaymentService paymentService;
    private final EmailService emailService;
    private final SecurityService securityService;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ProjectionRepository projectionRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public BuyTicketController(ServiceLocator serviceLocator, BuyTicketPresenter presenter) {
        this.presenter = presenter;
        this.paymentService = serviceLocator.getService(PaymentService.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
        this.movieRepository = serviceLocator.getService(MovieRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        this.customerRepository = serviceLocator.getService(CustomerRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
        this.seatRepository = serviceLocator.getService(SeatRepository.class);
    }

    @Override
    public void getMovieList(MovieListRequest request) {
        try {
            Request.validate(request);
            var localDate = request.getDate().toString();
            var movieList = movieRepository.getMovieList(localDate);
            presenter.presentMovieList(new MovieListResponse(MovieDataMapper.mapToDtoList(movieList)));
        } catch (Request.NullRequestException e) {
            presenter.presentMovieListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidMovieListRequest(request);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

    @Override
    public void getCinemaList(CinemaListRequest request) {
        try {
            Request.validate(request);
            var movie = movieRepository.getMovie(request.getMovieId());
            var cinemaList = cinemaRepository.getListCinema(movie, request.getData());
            presenter.presentCinemaList(
                    new CinemaListResponse(CinemaDataMapper.mapToDtoList(cinemaList))
            );
        } catch (Request.NullRequestException e) {
            presenter.presentCinemaListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidCinemaListRequest(request);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

    @Override
    public void getProjectionList(ProjectionListRequest request) {
        try {
            Request.validate(request);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            var movie = movieRepository.getMovie(request.getMovieId());
            var projectionList = projectionRepository.getProjectionList(cinema, movie, request.getLocalDate());
            presenter.presentProjectionList(
                    new ProjectionListResponse(ProjectionDataMapper.mapToDtoList(projectionList))
            );
        } catch (Request.NullRequestException e) {
            presenter.presentProjectionListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidProjectionListRequest(request);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

    @Override
    public void getSeatList(SeatListRequest request) {
        var permissions = new Permission[]{Permission.BUY_TICKET};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var projection = projectionRepository.getProjection(request.getProjectionId());
            presenter.presentSeatList(new SeatListResponse(
                    SeatDataMapper.mapToDtoList(projection.getHall().getSeatList(), projection::isBooked)));
        } catch (Request.NullRequestException e) {
            presenter.presentSeatListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidSeatListRequest(request);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        }
    }


    @Override
    public void pay(PaymentRequest request) {
        var permissions = new Permission[]{Permission.BUY_TICKET};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var customer = customerRepository.getCustomer(request.getUserId());
            var projection = projectionRepository.getProjection(request.getProjectionId());
            var seat = seatRepository.getSeat(request.getSeatId());
            if (projection.isBooked(seat)) {
                presenter.presentSeatAlreadyBookedError(new SeatErrorResponse("The place has already been booked"));
            } else {
                //-DECORATOR-//
                var ticket = new Ticket(0, projection.getBasePrice(), customer, seat, projection); // Ticket di base
                if (Boolean.TRUE.equals(request.getOpenBarOption())) {
                    ticket = new TicketOpenBar(ticket);
                }
                if (Boolean.TRUE.equals(request.getMagicBoxOption())) {
                    ticket = new TicketMagicBox(ticket);
                }
                if (Boolean.TRUE.equals(request.getSkipLineOption())) {
                    ticket = new TicketSkipLine(ticket);
                }
                //---------//
                paymentService.pay(new PayServiceRequest(
                        customer.getEmail(),
                        customer.getName(),
                        request.getCreditCardNumber(),
                        ticket.getPrice(),
                        request.getCreditCardCvv(),
                        request.getCreditCardExpirationDate()
                ));
                ticketRepository.saveTicket(ticket);
                emailService.sendMail(new EmailServiceRequest(request.getEmail(), "Payment receipt"));
                presenter.presentTicket(new TicketResponse(TicketDataMapper.mapToDto(ticket)));
            }
        } catch (Request.NullRequestException e) {
            presenter.presentPayNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidPayRequest(request);
        } catch (PaymentServiceException e) {
            presenter.presentPayPaymentServiceException(e);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        }
    }

}
