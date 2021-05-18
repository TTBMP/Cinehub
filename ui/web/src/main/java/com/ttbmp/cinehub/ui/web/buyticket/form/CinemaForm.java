package com.ttbmp.cinehub.ui.web.buyticket.form;

import com.ttbmp.cinehub.ui.web.domain.Cinema;
import com.ttbmp.cinehub.ui.web.domain.Movie;

public class CinemaForm {

    private String date;
    private Movie movie;
    private Cinema cinema;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

}
