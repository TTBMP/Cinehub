package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class CinemaListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");
    public static final Request.Error INVALID_MOVIE = new Request.Error("Cinema can't be null");

    private Integer movieId;
    private String date;

    public CinemaListRequest(Integer movieId, String date) {
        this.movieId = movieId;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public void onValidate() {
        if (date == null) {
            addError(MISSING_DATE_ERROR);
        }
        if (date != null) {
            var localDate = LocalDate.parse(date);
            if (localDate.isBefore(LocalDate.now())) {
                addError(MISSING_DATE_ERROR);
            }
        }
    }


}
