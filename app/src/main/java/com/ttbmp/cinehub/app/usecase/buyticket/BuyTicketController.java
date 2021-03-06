package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PayServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketMagicBox;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketOpenBar;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

import java.util.Objects;
import java.util.stream.Collectors;

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
        execute(presenter, request, () -> {
            Request.validate(request);
            var localDate = request.getDate().toString();
            var movieList = movieRepository.getMovieList(localDate).stream()
                    .map(MovieDto::new)
                    .collect(Collectors.toList());
            presenter.presentGetMovieList(new MovieListReply(movieList));
        });
    }

    @Override
    public void getCinemaList(CinemaListRequest request) {
        execute(presenter, request, () -> {
            Request.validate(request);
            var date = request.getDate();
            var movie = movieRepository.getMovie(request.getMovieId());
            if (movie == null) {
                request.addError(CinemaListRequest.MOVIE_ERROR);
            }
            if (date == null) {
                request.addError(CinemaListRequest.DATE_ERROR);
            }
            if (!request.getErrorList().isEmpty()) {
                throw new Request.InvalidRequestException();
            }
            var cinemaList = cinemaRepository.getListCinema(movie, date).stream()
                    .map(CinemaDto::new)
                    .collect(Collectors.toList());
            presenter.presentGetCinemaList(new CinemaListReply(cinemaList));
        });
    }


    @Override
    public void getProjectionList(ProjectionListRequest request) {
        execute(presenter, request, () -> {
            Request.validate(request);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            var movie = movieRepository.getMovie(request.getMovieId());
            if (movie == null) {
                request.addError(ProjectionListRequest.MOVIE_ERROR);
            }
            if (cinema == null) {
                request.addError(ProjectionListRequest.CINEMA_ERROR);
            }
            if (!request.getErrorList().isEmpty()) {
                throw new Request.InvalidRequestException();
            }
            var projectionList = projectionRepository.getProjectionList(cinema, movie, request.getDate()).stream()
                    .map(ProjectionDto::new)
                    .collect(Collectors.toList());
            presenter.presentGetProjectionList(new ProjectionListReply(projectionList));
        });
    }


    @Override
    public void getSeatList(SeatListRequest request) {
        execute(presenter, request, () -> {
            var permissions = new Permission[]{Permission.BUY_TICKET};
            AuthenticatedRequest.validate(request, securityService, permissions);
            var projection = projectionRepository.getProjection(request.getProjectionId());
            if (projection == null) {
                request.addError(SeatListRequest.PROJECTION_ERROR);
                throw new Request.InvalidRequestException();
            }
            var seatList = Objects.requireNonNull(projection).getHall().getSeatList().stream()
                    .map(seat -> new SeatDto(seat, projection.isBooked(seat)))
                    .collect(Collectors.toList());
            presenter.presentGetSeatList(new SeatListReply(seatList));
        });
    }


    @Override
    public void pay(PaymentRequest request) {
        execute(presenter, request, () -> {
            try {
                var permissions = new Permission[]{Permission.BUY_TICKET};
                AuthenticatedRequest.validate(request, securityService, permissions);
                var customer = customerRepository.getCustomer(request.getUserId());
                var projection = projectionRepository.getProjection(request.getProjectionId());
                var seat = seatRepository.getSeat(request.getSeatId());
                if (customer == null) {
                    request.addError(PaymentRequest.CUSTOMER_ERROR);
                }
                if (projection == null) {
                    request.addError(PaymentRequest.PROJECTION_ERROR);
                }
                if (seat == null) {
                    request.addError(PaymentRequest.SEAT_ERROR);
                }
                if (!request.getErrorList().isEmpty()) {
                    throw new Request.InvalidRequestException();
                }
                if (Objects.requireNonNull(projection).isBooked(seat)) {
                    presenter.presentSeatAlreadyBookedError(new SeatErrorReply("The place has already been booked"));
                } else {
                    var ticket = new Ticket(0, projection.getBasePrice(), customer, seat, projection);
                    ticket = addFunctionality(ticket, request);
                    paymentService.pay(new PayServiceRequest(
                            Objects.requireNonNull(customer).getEmail(),
                            customer.getName(),
                            request.getCreditCard().getCreditCardNumber(),
                            ticket.getPrice(),
                            request.getCreditCard().getCreditCardCvv(),
                            request.getCreditCard().getCreditCardExpirationDate()
                    ));
                    ticketRepository.saveTicket(ticket);
                    var message = "You bought the ticket at the price of " + ticket.getPrice() +
                            "€ for the hours " + projection.getStartTime() +
                            " in the hall " + projection.getHall().getName() + "\nSee you soon!";
                    emailService.sendMail(new EmailServiceRequest(request.getEmail(), message));
                    presenter.presentTicket(new TicketReply(new TicketDto(ticket)));
                }
            } catch (PaymentServiceException e) {
                presenter.presentPaymentServiceException(e);
            } catch (EmailServiceException e) {
                presenter.presentSendEmailServiceException(e);
            }
        });
    }

    //-DECORATOR-//
    private Ticket addFunctionality(Ticket ticket, PaymentRequest request) {
        if (Boolean.TRUE.equals(request.getTicketOption().getOpenBarOption())) {
            ticket = new TicketOpenBar(ticket);
        }
        if (Boolean.TRUE.equals(request.getTicketOption().getMagicBoxOption())) {
            ticket = new TicketMagicBox(ticket);
        }
        if (Boolean.TRUE.equals(request.getTicketOption().getSkipLineOption())) {
            ticket = new TicketSkipLine(ticket);
        }
        return ticket;
    }


}
