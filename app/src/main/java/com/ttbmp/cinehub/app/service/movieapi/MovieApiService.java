package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.domain.Movie;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public interface MovieApiService {

    Movie getMovie(int movieId) throws MovieApiServiceException;

}
