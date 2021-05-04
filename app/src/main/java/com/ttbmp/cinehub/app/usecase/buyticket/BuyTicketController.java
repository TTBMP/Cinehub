package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.*;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationException;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PayServiceRequest;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketMagicBox;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketOpenBar;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketController implements BuyTicketUseCase {

    private final BuyTicketPresenter buyTicketPresenter;
    private final PaymentService paymentService;
    private final EmailService emailService;
    private final AuthenticationService authenticationService;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ProjectionRepository projectionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final HallRepository hallRepository;

    public BuyTicketController(ServiceLocator serviceLocator, BuyTicketPresenter buyTicketPresenter) {
        this.buyTicketPresenter = buyTicketPresenter;
        this.paymentService = serviceLocator.getService(PaymentService.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.authenticationService = serviceLocator.getService(AuthenticationService.class);
        this.movieRepository = serviceLocator.getService(MovieRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
    }

    @Override
    public void pay(PaymentRequest request) {
        try {
            Request.validate(request);
            var user = userRepository.getUser(authenticationService.signIn("", "").getUserId());
            var ticket = TicketDataMapper.mapToEntity(request.getTicket());
            paymentService.pay(new PayServiceRequest(
                    user.getEmail(),
                    user.getName(),
                    request.getCreditCard().getNumber(),
                    ticket.getPrice()
            ));
            ticketRepository.saveTicket(ticket, request.getProjection().getId());
            emailService.sendMail(new EmailServiceRequest(request.getReceiptEmail(), "Payment receipt"));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentPayNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidPay(request);
        } catch (PaymentServiceException e) {
            buyTicketPresenter.presentErrorByStripe(e);
        } catch (AuthenticationException e) {
            buyTicketPresenter.presentAuthenticationError();
        } catch (RepositoryException e) {
            buyTicketPresenter.presentPayRepositoryException(e.getMessage());
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
        } catch (RepositoryException e) {
            buyTicketPresenter.presentGetListMovieRepositoryException(e.getMessage());
        }
    }

    @Override
    public void getListCinema(GetListCinemaRequest request) {
        try {
            Request.validate(request);
            var movie = movieRepository.getMovie(request.getMovieId());
            var date = request.getData();
            var cinemaList = cinemaRepository.getListCinema(movie, date);
            buyTicketPresenter.presentCinemaList(new GetListCinemaResponse(CinemaDataMapper.mapToDtoList(cinemaList)));
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetListCinemaNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetListCinema(request);
        } catch (RepositoryException e) {
            buyTicketPresenter.presentGetCinemaListRepositoryException(e.getMessage());
        }
    }

    @Override
    public void createTicket(GetTicketBySeatsRequest request) {
        try {
            Request.validate(request);
            var seats = SeatDataMapper.mapToEntityList(request.getSeatDtoList());
            var pos = request.getNumber();
            var seat = seats.get(pos);
            var user = userRepository.getUser(authenticationService.signIn("", "").getUserId());
            var projection = projectionRepository.getProjection(request.getProjectionId());

            /*-----------DECORATOR PATTERN GOF---------*/
            var ticket = new Ticket(0, projection.getBasePrice(), user, seat); //Ticket di base
            if (Boolean.TRUE.equals(request.getOpenBarOption())) {
                ticket = new TicketOpenBar(ticket);
            }
            if (Boolean.TRUE.equals(request.getMagicBoxOption())) {
                ticket = new TicketMagicBox(ticket);
            }
            if (Boolean.TRUE.equals(request.getSkipLineOption())) {
                ticket = new TicketSkipLine(ticket);
            }
            /*-----------------------------------------*/
            buyTicketPresenter.setSelectedTicket(new GetTicketBySeatsResponse(TicketDataMapper.mapToDto(ticket)));

        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTicketBySeatsNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTicketBySeats(request);
        } catch (AuthenticationException e) {
            buyTicketPresenter.presentAuthenticationError();
        } catch (RepositoryException e) {
            buyTicketPresenter.presentCreateTicketRepositoryException(e.getMessage());
        }
    }

    @Override
    public void getProjectionList(GetProjectionListRequest request) {
        try {
            Request.validate(request);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            var movie = movieRepository.getMovie(request.getMovieId());
            var date = request.getLocalDate();
            var projectionList = projectionRepository.getProjectionList(cinema, movie, date);
            buyTicketPresenter.presentProjectionList(
                    new GetProjectionListResponse(ProjectionDataMapper.mapToDtoList(projectionList))
            );
        } catch (Request.NullRequestException e) {
            buyTicketPresenter.presentGetTimeOfProjectionNullRequest();
        } catch (Request.InvalidRequestException e) {
            buyTicketPresenter.presentInvalidGetTimeOfProjection(request);
        } catch (RepositoryException e) {
            buyTicketPresenter.presentGetProjectionListRepositoryException(e.getMessage());
        }
    }


    @Override
    public void getProjection(GetProjectionRequest request) {
        try {
            Hall hall = hallRepository.getHall(request.getHallId());
            Projection projection = projectionRepository.getProjection(
                    request.getLocalDate(),
                    request.getStartTime(),
                    hall
            );
            buyTicketPresenter.presentProjection(new GetProjectionResponse(ProjectionDataMapper.mapToDto(projection)));
        } catch (RepositoryException e) {
            buyTicketPresenter.presentGetProjectionRepositoryException(e.getMessage());
        }

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
        } catch (RepositoryException e) {
            buyTicketPresenter.presentGetCinemaRepositoryException(e.getMessage());
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
