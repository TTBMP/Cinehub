package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.usecase.UseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketUseCase extends UseCase {

     boolean sendEmail();

     boolean pay(Ticket ticket, Projection projection, Integer index);

     void getListMovie();

     void getTicketBySeats(List<Seat> seats, String position, Integer pos);

     void getListCinema(Movie movie);

     void getTimeOfProjection(Movie movie, Cinema cinema);

     void confirmSeatsRandom();

     void confirmSeatsSpecific();

     void setProjection(Cinema cinema, Movie movie, String time);

     void getNumberOfSeats(Projection projection);
}
