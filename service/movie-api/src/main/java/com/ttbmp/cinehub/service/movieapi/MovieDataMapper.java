package com.ttbmp.cinehub.service.movieapi;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieDataMapper {

    private MovieDataMapper() {

    }

    public static MovieDto mapToDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getName(),
                movie.getVote(),
                movie.getOverview(),
                movie.getReleases(),
                movie.getImageUrl(),
                movie.getDuration()
        );
    }

    public static List<MovieDto> mapToDtoList(List<Movie> movieList) {
        return DataMapperHelper.mapList(movieList, MovieDataMapper::mapToDto);
    }

}
