package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
@SuppressWarnings("java:S107")
public class Projection {

    private int id;
    private String date;
    private String startTime;
    private Movie movie;
    private Hall hall;
    private Cinema cinema;
    private Projectionist projectionist;
    private List<Ticket> ticketList;

    public Projection(int id, String date, String startTime, Movie movie, Hall hall, Cinema cinema, Projectionist projectionist, List<Ticket> ticketList) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.movie = movie;
        this.hall = hall;
        this.cinema = cinema;
        this.projectionist = projectionist;
        this.ticketList = ticketList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Projectionist getProjectionist() {
        return projectionist;
    }

    public void setProjectionist(Projectionist projectionist) {
        this.projectionist = projectionist;
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
