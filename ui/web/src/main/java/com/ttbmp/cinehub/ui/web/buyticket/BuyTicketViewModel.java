package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.*;

import java.util.List;

/**
 * @author Palmieri Ivan
 */

public class BuyTicketViewModel {

    private List<MovieDto> movieDtoList;
    private List<CinemaDto> cinemaDtoList;
    private List<ProjectionDto> projectionList;
    private List<SeatDto> seatList;
    private MovieDto selectedMovie;
    private String selectedDate;
    private CinemaDto selectedCinema;
    private ProjectionDto selectedProjection;
    private TicketDto selectedTicket;
    private Integer selectedPosition;
    private String emailError;
    private String movieError;
    private String cinemaError;
    private String seatError;
    private String paymentError;
    private List<SeatDto> seatDtoList;


    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    public void setCinemaError(String cinemaError) {
        this.cinemaError = cinemaError;
    }

    public void setSeatError(String seatError) {
        this.seatError = seatError;
    }

    public String getPaymentError() {
        return paymentError;
    }

    public void setPaymentError(String paymentError) {
        this.paymentError = paymentError;
    }

    public Integer getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Integer selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public TicketDto getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketDto selectedTicket) {
        this.selectedTicket = selectedTicket;
    }

    public List<SeatDto> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<SeatDto> seatList) {
        this.seatList = seatList;
    }

    public ProjectionDto getSelectedProjection() {
        return selectedProjection;
    }

    public void setSelectedProjection(ProjectionDto selectedProjection) {
        this.selectedProjection = selectedProjection;
    }

    public CinemaDto getSelectedCinema() {
        return selectedCinema;
    }

    public void setSelectedCinema(CinemaDto selectedCinema) {
        this.selectedCinema = selectedCinema;
    }

    public MovieDto getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(MovieDto selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<ProjectionDto> getProjectionList() {
        return projectionList;
    }

    public void setProjectionList(List<ProjectionDto> projectionList) {
        this.projectionList = projectionList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

    public void setSeatDtoList(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<CinemaDto> getCinemaDtoList() {
        return cinemaDtoList;
    }

    public void setCinemaDtoList(List<CinemaDto> cinemaDtoList) {
        this.cinemaDtoList = cinemaDtoList;
    }

    public List<MovieDto> getMovieDtoList() {
        return movieDtoList;
    }

    public void setMovieDtoList(List<MovieDto> movieDtoList) {
        this.movieDtoList = movieDtoList;
    }

}
