package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.usecase.Request;

/**
 * @author Ivan Palmieri
 */
public class GetListCinemaRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");
    private int movieId;
    private String data;

    public GetListCinemaRequest(int movieId, String data) {
        this.movieId = movieId;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public void onValidate() {
        if (data == null) {
            addError(MISSING_DATE_ERROR);
        }

    }
}
