package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.usecase.Request;

public class PaymentRequest extends Request {
    private final String position;
    private final  Integer number;

    private final  int hallId;
    private final String date;
    private final  int movieId;
    private final  int cinemaId;

    public PaymentRequest(
            String position,
            Integer number,
            int hallId,
            String date,
            int movieId,
            int cinemaId
    ) {
        this.position = position;
        this.number = number;
        this.hallId = hallId;
        this.date = date;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
    }

    public String getPosition() {
        return position;
    }

    public Integer getNumber() {
        return number;
    }


    public int getHallId() {
        return hallId;
    }

    public String getDate() {
        return date;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    @Override
    protected void onValidate() {

    }
}
