package com.ttbmp.cinehub.service.movieapi.datamapper;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.service.movieapi.dto.MovieApiDto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieApiDataMapper {

    private MovieApiDataMapper() {
    }

    public static MovieDto mapToDto(MovieApiDto movie) {

        return new MovieDto(
                movie.getId(),
                movie.getMovieName(),
                movie.getMovieVote(),
                movie.getMovieOverview(),
                movie.getMovieReleases(),
                movie.getMovieImageUrl());
    }


    public static List<MovieDto> mapToDtoList(List<MovieApiDto> movieList) {
        return DataMapperHelper.mapList(movieList, MovieApiDataMapper::mapToDto);
    }


}
