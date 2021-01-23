package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.entity.*;

import com.ttbmp.cinehub.core.repository.*;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.core.service.payment.PaymentService;

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
    public boolean sendEmail() {
        User user = new User("Ivan","palm@593.cos",new CreditCard("22/24",354,"4242424242424242","5496"));
        emailService.sendMail(user.getEmail(), "Hello Member", "Thanks for joining us.");
        return true;
    }
    @Override
    public boolean pay(Ticket ticket, Projection projection, Integer index) {
        User user = new User("Ivan","palm@593.cos",new CreditCard("22/24",354,"4242424242424242","5496"));
        if (paymentService.pay(user, ticket)) {
            //salvare i dati nella repository
            ticket.setState(true);
            projection.addTicket(ticket);
            projection.getHall().getSeatList().get(index).setState(false);
            user.addTicket(ticket);
            return true;
        }
        return false;
    }
    @Override
    public void getListMovie() {
        buyTicketPresenter.presentMovieApiList(movieRepository.getAllMovieByApi(movieApiService));
    }
    @Override
    public void getTicketBySeats(List<Seat> seats, String position, Integer pos) {
        Seat selectedSeats = seats.get(pos);
        Ticket ticket = new Ticket(selectedSeats.getPrice());
        ticket.setPosition(position);
        buyTicketPresenter.setSelectedTicket(ticket);

    }
    @Override
    public void getListCinema(Movie movie) {
        List<Projection> projectionList = projectionRepository.getProjectionByMovie(movie);
        List<Cinema> cinemaList = cinemaRepository.getCinemaByProjection(projectionList);
        buyTicketPresenter.presentCinemaList(cinemaList);

    }
    @Override
    public void getTimeOfProjection(Movie movie, Cinema cinema) {
        List<String> timeList = new ArrayList<>();
        if(cinema!=null) {
            timeList= projectionRepository.getTimeOfProjectionByMovieEndCinema(movie, cinema);
        }
        buyTicketPresenter.presentTimeList(timeList);
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
    public void setProjection(Cinema cinema, Movie movie, String time) {
        Projection projection = projectionRepository.getTimeOfProjectionByMovieEndCinemaEndTime(movie, cinema, time);
        buyTicketPresenter.presentProjection(projection);
    }
    @Override
    public void getNumberOfSeats(Projection projection) {
        Hall hall = projection.getHall();
        List<Seat> seatList = hall.getSeatList();
        buyTicketPresenter.presentSeatList(seatList);
    }
}
