package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.MovieDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class MovieListResponse {

    private final List<MovieDto> movieList;

    public MovieListResponse(List<MovieDto> movieList) {
        this.movieList = movieList;
    }

    public List<MovieDto> getMovieList() {
        return movieList;
    }

}
