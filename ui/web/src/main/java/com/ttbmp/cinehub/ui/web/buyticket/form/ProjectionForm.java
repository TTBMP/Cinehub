package com.ttbmp.cinehub.ui.web.buyticket.form;

import com.ttbmp.cinehub.ui.web.domain.Cinema;
import com.ttbmp.cinehub.ui.web.domain.Movie;
import com.ttbmp.cinehub.ui.web.domain.Projection;

public class ProjectionForm {

    private String date;
    private Movie movie;
    private Cinema cinema;
    private Projection projection;

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

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

}
