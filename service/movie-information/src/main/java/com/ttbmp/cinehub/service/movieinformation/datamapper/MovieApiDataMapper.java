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
            Movie movie = new Movie(movieApiDto.getName());
            movie.setVote(movieApiDto.getVote());
            movie.setOverview(movieApiDto.getOverview());
            movie.setRelases(movieApiDto.getRelases());
            movie.setDuration(movieApiDto.getDuration());
            movie.setImageUrl(movieApiDto.getImageUrl());
            listMovie.add(movie);
        }
        return listMovie;

    }
}
