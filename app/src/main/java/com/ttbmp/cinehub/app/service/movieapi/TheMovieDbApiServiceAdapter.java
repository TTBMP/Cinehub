package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.service.movieapi.TheMovieDbApiException;
import com.ttbmp.cinehub.service.movieapi.TheMovieDbApiService;

/**
 * @author Fabio Buracchi
 */
public class TheMovieDbApiServiceAdapter implements MovieApiService {

    private final TheMovieDbApiService service = new TheMovieDbApiService();

    @Override
    public Movie getMovie(int movieId) throws MovieApiServiceException {
        Movie result;
        try {
            com.ttbmp.cinehub.service.movieapi.Movie movie = service.getMovie(movieId);
            result = new Movie(
                    movie.getId(),
                    movie.getName(),
                    movie.getOverview(),
                    Integer.parseInt(movie.getDuration()),
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
