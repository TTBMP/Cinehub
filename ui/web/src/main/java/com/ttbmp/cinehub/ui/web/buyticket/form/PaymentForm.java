package com.ttbmp.cinehub.ui.web.buyticket.form;

import com.ttbmp.cinehub.ui.web.domain.Cinema;
import com.ttbmp.cinehub.ui.web.domain.Movie;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import com.ttbmp.cinehub.ui.web.domain.Seat;

public class PaymentForm {

    private String date;
    private Movie movie;
    private Cinema cinema;
    private Projection projection;
    private Seat seat;
    private Boolean option1;
    private Boolean option2;
    private Boolean option3;
    private String email;
    private String cvv;
    private String expirationDate;
    private String numberCard;

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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Boolean getOption1() {
        return option1;
    }

    public void setOption1(Boolean option1) {
        this.option1 = option1;
    }

    public Boolean getOption2() {
        return option2;
    }

    public void setOption2(Boolean option2) {
        this.option2 = option2;
    }

    public Boolean getOption3() {
        return option3;
    }

    public void setOption3(Boolean option3) {
        this.option3 = option3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

}
