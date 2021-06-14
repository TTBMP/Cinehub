package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class ProjectionListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");
    public static final Request.Error INVALID_MOVIE = new Request.Error("Movie can't be null");
    public static final Request.Error INVALID_CINEMA = new Request.Error("Cinema can't be null");


    private Integer movieId;
    private Integer cinemaId;
    private String date;

    public ProjectionListRequest(Integer movieId, Integer cinemaId, LocalDate date) {
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.date = date.toString();
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

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public void onValidate() {
        if(date == null){
            addError(MISSING_DATE_ERROR);
        }
        if(date != null){
            var localDate = LocalDate.parse(this.date);
            if (localDate.isBefore(LocalDate.now())) {
                addError(MISSING_DATE_ERROR);
            }
        }

    }


}
