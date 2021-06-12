package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.MovieDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class MovieListReply {

    private final List<MovieDto> movieList;

    public MovieListReply(List<MovieDto> movieList) {
        this.movieList = movieList;
    }

    public List<MovieDto> getMovieList() {
        return movieList;
    }

}
