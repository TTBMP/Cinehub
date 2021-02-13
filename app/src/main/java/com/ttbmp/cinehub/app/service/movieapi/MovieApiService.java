package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.domain.Movie;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public interface MovieApiService {

    Movie getMovie(int movieId) throws MovieApiServiceException;

}
