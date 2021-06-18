package com.ttbmp.cinehub.ui.web.buyticket;

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
    private Boolean skipLineOption;
    private Boolean magicBoxOption;
    private Boolean openBarOption;
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

    public Boolean getSkipLineOption() {
        return skipLineOption;
    }

    public void setSkipLineOption(Boolean skipLineOption) {
        this.skipLineOption = skipLineOption;
    }

    public Boolean getMagicBoxOption() {
        return magicBoxOption;
    }

    public void setMagicBoxOption(Boolean magicBoxOption) {
        this.magicBoxOption = magicBoxOption;
    }

    public Boolean getOpenBarOption() {
        return openBarOption;
    }

    public void setOpenBarOption(Boolean openBarOption) {
        this.openBarOption = openBarOption;
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
