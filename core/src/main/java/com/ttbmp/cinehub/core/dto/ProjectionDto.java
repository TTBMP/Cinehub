package com.ttbmp.cinehub.core.dto;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;


/**
 * @author Palmieri Ivan
 */
public class ProjectionDto {

    private String startTime;
    private Cinema cinemaDto;
    private Hall hallDto;
    private Movie movieDto;
    private Ticket ticketList;
    private String date;

    public ProjectionDto(Movie movie, Cinema cinema, Hall hall, String startTime, String date) {
        this.movieDto = movie;
        this.cinemaDto = cinema;
        this.hallDto = hall;
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

    public Cinema getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(Cinema cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    public Hall getHallDto() {
        return hallDto;
    }

    public void setHallDto(Hall hallDto) {
        this.hallDto = hallDto;
    }

    public Movie getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(Movie movieDto) {
        this.movieDto = movieDto;
    }

    public Ticket getTicketList() {
        return ticketList;
    }

    public void setTicketList(Ticket ticketList) {
        this.ticketList = ticketList;
    }
}
