package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieDataMapper {

    private MovieDataMapper() {
    }

    public static MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto(movie.getId(), movie.getName(), movie.getVote(), movie.getOverview(), movie.getOverview(), movie.getImageUrl());
        /*movieDto.setOverview(movie.getOverview());
        movieDto.setReleases(movie.getRelases());
        movieDto.setVote(movie.getVote());
        movieDto.setMovieUrl(movie.getImageUrl());*/
        return movieDto;
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
