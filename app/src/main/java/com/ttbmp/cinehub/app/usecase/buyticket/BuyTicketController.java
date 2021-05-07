package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.*;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PayServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.*;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketFoldingArmchair;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketHeatedArmchair;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

import java.io.IOException;
import java.util.List;

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
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public BuyTicketController(ServiceLocator serviceLocator, BuyTicketPresenter buyTicketPresenter) {
        this.buyTicketPresenter = buyTicketPresenter;
        this.paymentService = serviceLocator.getService(PaymentService.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
        this.movieRepository = serviceLocator.getService(MovieRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        this.customerRepository = serviceLocator.getService(CustomerRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
    }

    @Override
    public void pay(PayRequest request) {
        try {
            Request.validate(request);
            Customer customer = customerRepository.getCustomer(securityService.authenticate("").getId());
            Ticket ticket = TicketDataMapper.mapToEntity(request.getTicket());
            paymentService.pay(new PayServiceRequest(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getCreditCard().getNumber(),
                    ticket.getPrice()
            ));
            ticket.setOwner(customer);
            ticketRepository.saveTicket(ticket, request.getProjection().getId());
            emailService.sendMail(new EmailServiceRequest(
                    customer.getEmail(),
                    "Payment receipt"
            ));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentPayNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidPay(request);
        } catch (PaymentServiceException e) {
            buyTicketPresenter.presentErrorByStripe(e);
        } catch (SecurityException e) {
            buyTicketPresenter.presentAutenticationError();
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
            Seat seat = seats.get(pos);
            Customer customer = customerRepository.getCustomer(securityService.authenticate("").getId());
            /*DECORATOR PATTERN GOF*/
            Ticket ticket = new Ticket(0, seat.getPrice(), customer, seat);
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
            buyTicketPresenter.presentAutenticationError();
        }
    }

    /*To find the times of the screenings given a film, a cinema and a date*/
    @Override
    public void getProjectionList(GetProjectionRequest request) {
        try {
            Request.validate(request);
            Cinema cinema = CinemaDataMapper.mapToEntity(request.getCinemaDto());
            Movie movie = MovieDataMapper.mapToEntity(request.getMovieDto());
            String date = request.getLocalDate();
            List<Projection> projectionList = projectionRepository.getProjectionList(
                    cinema,
                    movie,
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
