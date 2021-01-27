package com.ttbmp.cinehub.core.dto;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketNormal;


/**
 * @author Palmieri Ivan
 */
public class ProjectionDto {

    private String startTime;
    private CinemaDto cinemaDto;
    private HallDto hallDto;
    private MovieDto movieDto;
    private TicketNormal ticketNormalList;
    private String date;

    public ProjectionDto(MovieDto movie, CinemaDto cinema, HallDto hall, String startTime, String date) {
        this.movieDto = movie;
        this.cinemaDto = cinema;
        this.hallDto = hall;
        this.startTime = startTime;
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    public HallDto getHallDto() {
        return hallDto;
    }

    public void setHallDto(HallDto hallDto) {
        this.hallDto = hallDto;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public TicketNormal getTicketNormalList() {
        return ticketNormalList;
    }

    public void setTicketNormalList(TicketNormal ticketNormalList) {
        this.ticketNormalList = ticketNormalList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
