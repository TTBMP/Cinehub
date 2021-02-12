package com.ttbmp.cinehub.domain;


import com.ttbmp.cinehub.domain.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class Projection {

    private final String startTime;
    private final Hall hall;
    private Cinema cinema;
    private Movie movie;
    private List<Ticket> ticketList = new ArrayList<>();
    private String date;

    public Projection(Movie movie, Cinema cinema, Hall hall, String startTime, String date) {
        this.movie = movie;
        this.cinema = cinema;
        this.hall = hall;
        this.startTime = startTime;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Hall getHall() {
        return hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public void addTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

}
