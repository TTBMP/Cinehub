package com.ttbmp.cinehub.service.movieinformation.datamapper;

import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.service.movieinformation.dto.MovieApiDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieApiDataMapper {

    private MovieApiDataMapper(){}

    public static List<Movie> mapToEntityList(List<MovieApiDto> movieList) {

        List<Movie> listMovie = new ArrayList<>();
        for (MovieApiDto movieApiDto : movieList) {
            Movie movie = new Movie(movieApiDto.getMovieName());
            movie.setVote(movieApiDto.getMovieVote());
            movie.setOverview(movieApiDto.getMovieOverview());
            movie.setRelases(movieApiDto.getMovieReleases());
            movie.setImageUrl(movieApiDto.getMovieImageUrl());
            listMovie.add(movie);
        }
        return listMovie;

    }
}
