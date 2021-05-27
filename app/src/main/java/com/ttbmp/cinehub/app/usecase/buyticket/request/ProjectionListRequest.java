package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;

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
        if (LocalDate.parse(localDate).isBefore(LocalDate.now())) {
            addError(MISSING_DATE_ERROR);
        }
    }

    public void semanticValidate(Movie movie, Cinema cinema) throws InvalidRequestException {
        if (movie == null) {
            addError(INVALID_MOVIE);
        }
        if (cinema == null) {
            addError(INVALID_CINEMA);
        }
        if (!getErrorList().isEmpty()) {
            throw new InvalidRequestException();
        }
    }

}
