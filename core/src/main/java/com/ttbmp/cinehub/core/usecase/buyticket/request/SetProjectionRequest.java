package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class SetProjectionRequest extends Request {

    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_TIME_ERROR = new Request.Error("Time can't be after the end time");


    private MovieDto movieDto;
    private CinemaDto cinemaDto;
    private String time;

    public SetProjectionRequest(MovieDto movieDto, CinemaDto cinemaDto, String time) {
        this.movieDto = movieDto;
        this.cinemaDto = cinemaDto;
        this.time = time;
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

    }
}
