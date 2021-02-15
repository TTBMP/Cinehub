package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class SetSelectedProjectionRequest extends Request {

    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_TIME_ERROR = new Request.Error("Time can't be after the end time");


    private MovieDto movieDto;
    private CinemaDto cinemaDto;
    private String time;
    private String date;

    public SetSelectedProjectionRequest(MovieDto movieDto, CinemaDto cinemaDto, String time, LocalDate date) {
        this.movieDto = movieDto;
        this.cinemaDto = cinemaDto;
        this.time = time;
        this.date = date.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public void onValidate() {
        if (movieDto == null) {
            addError(MISSING_MOVIE_ERROR);
        }
        if (cinemaDto == null) {
            addError(MISSING_CINEMA_ERROR);

        }
        if (time == null) {
            addError(MISSING_TIME_ERROR);
        }
    }
}
