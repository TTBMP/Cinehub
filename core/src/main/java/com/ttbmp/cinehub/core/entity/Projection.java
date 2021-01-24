package com.ttbmp.cinehub.core.entity;

import java.util.ArrayList;
import java.util.List;

public class Projection {

    private String startTime;
    private Cinema cinema;
    private Hall hall;
    private Movie movie;
    private List<Ticket> ticketList = new ArrayList<>();

    public Projection(Movie movie, Cinema cinema, Hall hall, String startTime) {
        this.movie = movie;
        this.cinema = cinema;
        this.hall = hall;
        this.startTime = startTime;
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
