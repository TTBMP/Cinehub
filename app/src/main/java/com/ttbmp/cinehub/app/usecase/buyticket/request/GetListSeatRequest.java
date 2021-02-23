package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.usecase.Request;

public class GetListSeatRequest extends Request {
    public static final Request.Error MISSING_HALL_ERROR = new Request.Error("Hall can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");
    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");

    private final int hallId;
    private final String date;
    private final int movieId;
    private final int cinemaId;

    public GetListSeatRequest(int hallId, String date, int movieId, int cinemaId) {
        this.hallId = hallId;
        this.date = date;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
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
        if(hallId<0){
            addError(MISSING_HALL_ERROR);
        }
        if(movieId<0){
            addError(MISSING_MOVIE_ERROR);

        }
        if(date.isEmpty()){
            addError(MISSING_DATE_ERROR);

        }
        if(cinemaId<0){
            addError(MISSING_CINEMA_ERROR);

        }

    }
}
