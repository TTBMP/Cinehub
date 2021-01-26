package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.usecase.Request;
/**
 * @author Palmieri Ivan
 */
public class GetTimeOfProjecitonRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");


    private MovieDto movieDto;
    private CinemaDto cinemaDto;

    public GetTimeOfProjecitonRequest(MovieDto movieDto, CinemaDto cinemaDto) {
        this.movieDto = movieDto;
        this.cinemaDto = cinemaDto;
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
