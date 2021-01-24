package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.dto.MovieDto;

import java.util.List;

public class GetListMovieResponse {

    private List<MovieDto> movieList;

    public GetListMovieResponse(List<MovieDto> movieList) {
        this.movieList = movieList;
    }

    public List<MovieDto> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieDto> movieList) {
        this.movieList = movieList;
    }
}
