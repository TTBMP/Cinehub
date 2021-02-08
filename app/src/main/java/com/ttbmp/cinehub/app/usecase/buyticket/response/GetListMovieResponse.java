package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.MovieDto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class GetListMovieResponse {

    private final List<MovieDto> movieList;

    public GetListMovieResponse(List<MovieDto> movieList) {
        this.movieList = movieList;
    }

    public List<MovieDto> getMovieList() {
        return movieList;
    }

}
