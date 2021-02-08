package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
public class GetTimeOfProjectionRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");


    private MovieDto movieDto;
    private CinemaDto cinemaDto;
    private String localDate;

    public GetTimeOfProjectionRequest(MovieDto movieDto, CinemaDto cinemaDto, LocalDate localDate) {
        this.movieDto = movieDto;
        this.cinemaDto = cinemaDto;
        this.localDate = localDate.toString();
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    @Override
    public void onValidate() {
        if (movieDto == null) {
            addError(MISSING_MOVIE_ERROR);
        }
        if (localDate == null) {
            addError(MISSING_DATE_ERROR);
        }

    }
}
