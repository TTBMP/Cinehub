package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.employee.Projectionist;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class ProjectionDto {

    private int id;
    private String date;
    private final String startTime;
    private MovieDto movieDto;
    private final HallDto hallDto;
    private CinemaDto cinemaDto;
    private Projectionist projectionist;
    private List<TicketDto> listTicket;

    public ProjectionDto(MovieDto movie,
                         CinemaDto cinema,
                         HallDto hall,
                         String startTime,
                         String date,
                         List<TicketDto> listTicket,
                         int id,
                         Projectionist projectionist
    ) {
        this.id = id;
        this.projectionist = projectionist;
        this.movieDto = movie;
        this.cinemaDto = cinema;
        this.hallDto = hall;
        this.startTime = startTime;
        this.date = date;
        this.listTicket = listTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projectionist getProjectionist() {
        return projectionist;
    }

    public void setProjectionist(Projectionist projectionist) {
        this.projectionist = projectionist;
    }

    public List<TicketDto> getListTicket() {
        return listTicket;
    }

    public void setListTicket(List<TicketDto> listTicket) {
        this.listTicket = listTicket;
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
