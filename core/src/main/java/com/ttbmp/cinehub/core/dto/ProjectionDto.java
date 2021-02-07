package com.ttbmp.cinehub.core.dto;


import java.util.List;


/**
 * @author Palmieri Ivan
 */
public class ProjectionDto {

    private final String startTime;
    private final HallDto hallDto;
    private CinemaDto cinemaDto;
    private MovieDto movieDto;
    private List<TicketDto> ticketBasicList;
    private String date;

    public ProjectionDto(MovieDto movie, CinemaDto cinema, HallDto hall, String startTime, String date) {
        this.movieDto = movie;
        this.cinemaDto = cinema;
        this.hallDto = hall;
        this.startTime = startTime;
        this.date = date;
    }

    public List<TicketDto> getTicketBasicList() {
        return ticketBasicList;
    }

    public void setTicketBasicList(List<TicketDto> ticketBasicList) {
        this.ticketBasicList = ticketBasicList;
    }

    public String getStartTime() {
        return startTime;
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

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
