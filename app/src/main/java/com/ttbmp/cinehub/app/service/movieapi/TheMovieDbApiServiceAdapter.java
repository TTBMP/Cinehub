package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.service.movieapi.Movie;
import com.ttbmp.cinehub.service.movieapi.TheMovieDbApiException;
import com.ttbmp.cinehub.service.movieapi.TheMovieDbApiService;

/**
 * @author Fabio Buracchi
 */
public class TheMovieDbApiServiceAdapter implements MovieApiService {

    private final TheMovieDbApiService service = new TheMovieDbApiService();

    @Override
    public MovieDto getMovie(int movieId) throws MovieApiServiceException {
        MovieDto result;
        try {
            Movie movie = service.getMovie(movieId);
            result = new MovieDto(
                    movie.getId(),
                    movie.getName(),
                    movie.getOverview(),
                    movie.getDuration(),
                    movie.getImageUrl(),
                    movie.getVote(),
                    movie.getReleases()
            );
        } catch (TheMovieDbApiException e) {
            throw new MovieApiServiceException(e.getMessage());
        }
        return result;
    }

}
