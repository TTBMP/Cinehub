package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class GetListCinemaRequest extends Request {
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    private MovieDto movieDto;

    public GetListCinemaRequest(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    @Override
    public void onValidate() {

    }
}
