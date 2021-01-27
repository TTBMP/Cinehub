package com.ttbmp.cinehub.service.movieinformation.datamapper;

import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.service.movieinformation.dto.MovieApiDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieApiDataMapper {

    private MovieApiDataMapper() {
    }

    public static List<MovieDto> mapToDtoList(List<MovieApiDto> movieList) {

        List<MovieDto> movieDtoList = new ArrayList<>();
        for (MovieApiDto movieApiDto : movieList) {
            MovieDto movieDto = new MovieDto(movieApiDto.getMovieName());
            movieDto.setVote(movieApiDto.getMovieVote());
            movieDto.setOverview(movieApiDto.getMovieOverview());
            movieDto.setReleases(movieApiDto.getMovieReleases());
            movieDto.setMovieUrl(movieApiDto.getMovieImageUrl());
            movieDtoList.add(movieDto);
        }
        return movieDtoList;

    }
}
