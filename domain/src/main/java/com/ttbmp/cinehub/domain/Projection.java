package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class Projection {

    private int id;
    private String date;
    private String startTime;
    private Movie movie;
    private Hall hall;
    private Projectionist projectionist;
    private List<Ticket> ticketList;
    private long basePrice;

    public Projection(int id, String date, String startTime, Movie movie, Hall hall, Projectionist projectionist, List<Ticket> ticketList, long basePrice) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.movie = movie;
        this.hall = hall;
        this.projectionist = projectionist;
        this.ticketList = ticketList;
        this.basePrice = basePrice;
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

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isBooked(Seat seat) {
        return getTicketList().stream().anyMatch(t -> t.getSeat().getId() == seat.getId());
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (Projection) obj;
        return this.getId() == other.getId()
                && this.getDate().equals(other.getDate())
                && LocalTime.parse(this.getStartTime()).equals(LocalTime.parse(other.getStartTime()))
                && this.getMovie().equals(other.getMovie())
                && this.getHall().equals(other.getHall())
                && this.getProjectionist().equals(other.getProjectionist())
                && this.getTicketList().containsAll(other.getTicketList())
                && this.getBasePrice() == other.getBasePrice();
    }

}
