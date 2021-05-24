package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class ProjectionListRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");

    private Integer movieId;
    private Integer cinemaId;
    private String localDate;

    public ProjectionListRequest(Integer movieId, Integer cinemaId, LocalDate localDate) {
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.localDate = localDate.toString();
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public void onValidate() {
        if (movieId == null) {
            addError(MISSING_MOVIE_ERROR);
        }
        if (localDate == null) {
            addError(MISSING_DATE_ERROR);
        }
        if (cinemaId == null) {
            addError(MISSING_CINEMA_ERROR);
        }
    }

}
