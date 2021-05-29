package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Movie;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class CinemaListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");
    public static final Request.Error INVALID_MOVIE = new Request.Error("Cinema can't be null");

    private int movieId;
    private String date;

    public CinemaListRequest(int movieId, String date) {
        this.movieId = movieId;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public void onValidate() {
        if (LocalDate.parse(date).isBefore(LocalDate.now())) {
            addError(MISSING_DATE_ERROR);
        }
    }



}
