package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.usecase.Request;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
public class GetTimeOfProjecitonRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");


    private MovieDto movieDto;
    private CinemaDto cinemaDto;
    private String localDate;

    public GetTimeOfProjecitonRequest(MovieDto movieDto, CinemaDto cinemaDto, LocalDate localDate) {
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
        if(movieDto==null){
            addError(MISSING_MOVIE_ERROR);
        }
        if(cinemaDto==null){
            addError(MISSING_CINEMA_ERROR);
        }

    }
}