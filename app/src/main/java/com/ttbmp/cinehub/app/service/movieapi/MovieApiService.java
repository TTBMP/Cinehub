package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.app.dto.MovieDto;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public interface MovieApiService {

    MovieDto getMovie(int movieId) throws MovieApiServiceException;

}
