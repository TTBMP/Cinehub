package com.ttbmp.cinehub.core.entity;


import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class Projection {

    private String startTime;
    private Cinema cinema;
    private Hall hall;
    private Movie movie;
    private List<Ticket> ticket = new ArrayList<>();
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public void addTicket(Ticket ticket) {
        this.ticket.add(ticket);
    }
}
