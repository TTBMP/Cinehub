package com.ttbmp.cinehub.core.entity;

import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;

import java.util.ArrayList;
import java.util.List;

public class Projection {

    private String startTime;
    private Cinema cinema;
    private Hall hall;
    private Movie movie;
    private List<TicketAbstract> ticketAbstractList = new ArrayList<>();
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

    public List<TicketAbstract> getTicketAbstractList() {
        return ticketAbstractList;
    }

    public void setTicketAbstractList(List<TicketAbstract> ticketAbstractList) {
        this.ticketAbstractList = ticketAbstractList;
    }

    public void addTicket(TicketAbstract ticketAbstract) {
        this.ticketAbstractList.add(ticketAbstract);
    }
}
