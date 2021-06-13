package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.dto.*;

import java.util.List;

public class MockBuyTicketViewModel {

    private String sessionToken;
    private String errorMessage;
    private List<MovieDto> movieList;
    private List<CinemaDto> cinemaList;
    private List<ProjectionDto> projectionList;
    private List<SeatDto> seatList;
    private TicketDto ticket;

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }

    public List<ProjectionDto> getProjectionList() {
        return projectionList;
    }

    public void setProjectionList(List<ProjectionDto> projectionList) {
        this.projectionList = projectionList;
    }

    public List<SeatDto> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<SeatDto> seatList) {
        this.seatList = seatList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<CinemaDto> cinemaDto) {
        this.cinemaList = cinemaDto;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public List<MovieDto> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieDto> movieList) {
        this.movieList = movieList;
    }
}
