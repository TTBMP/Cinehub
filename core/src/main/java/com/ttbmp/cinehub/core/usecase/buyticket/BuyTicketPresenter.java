package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.entity.*;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(List<Movie> listMovieName);

    void presentCinemaList(List<Cinema> cinemaByMovie);

    void presentTimeList(List<String> timeOfProjectionList);

    void presentSeatList(List<Seat> listSeat);

    void confirmSeatsSpecific();

    void confirmSeatsRandom();

    void setSelectedTicket(Ticket ticket);


    void presentProjection(Projection projection);
}
