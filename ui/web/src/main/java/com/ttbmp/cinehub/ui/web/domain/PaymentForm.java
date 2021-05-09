package com.ttbmp.cinehub.ui.web.domain;

public class PaymentForm {

    private String email;
    private String cvv;
    private String expirationDate;
    private String numberCard;
    private Boolean option1;
    private Boolean option2;
    private Boolean option3;
    private String position;
    private int number;
    private int cinemaId;
    private int movieId;
    private int hallId;
    private String date;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}