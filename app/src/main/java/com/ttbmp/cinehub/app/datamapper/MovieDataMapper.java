package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.domain.Movie;
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
                movie.getRelases(),
                movie.getImageUrl()
        );
    }

    public static Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setOverview(movieDto.getOverview());
        movie.setRelases(movieDto.getReleases());
        movie.setVote(movieDto.getVote());
        movie.setImageUrl(movieDto.getMovieUrl());
        return movie;
    }

    public static List<MovieDto> mapToDtoList(List<Movie> movieList) {
        return DataMapperHelper.mapList(movieList, MovieDataMapper::mapToDto);
    }

    public static List<Movie> mapToEntityList(List<MovieDto> movieList) {
        return DataMapperHelper.mapList(movieList, MovieDataMapper::mapToEntity);
    }


}
