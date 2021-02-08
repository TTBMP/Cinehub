package com.ttbmp.cinehub.domain.usecase.buyticket.response;

import com.ttbmp.cinehub.domain.dto.MovieDto;

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
