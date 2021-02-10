package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Movie;

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
                movie.getOverview(),
                Integer.toString(movie.getDuration()),
                movie.getImageUrl(),
                movie.getRating(),
                movie.getReleaseDate()
        );
    }

    public static Movie mapToEntity(MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getName(),
                movieDto.getOverview(),
                Integer.parseInt(movieDto.getDuration()),
                movieDto.getMovieUrl(),
                movieDto.getVote(),
                movieDto.getReleases()
        );
    }

    public static List<MovieDto> mapToDtoList(List<Movie> movieList) {
        return DataMapperHelper.mapList(movieList, MovieDataMapper::mapToDto);
    }

    public static List<Movie> mapToEntityList(List<MovieDto> movieList) {
        return DataMapperHelper.mapList(movieList, MovieDataMapper::mapToEntity);
    }


}
