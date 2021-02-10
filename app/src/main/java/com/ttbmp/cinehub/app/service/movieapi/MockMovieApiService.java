package com.ttbmp.cinehub.app.service.movieapi;


import com.ttbmp.cinehub.app.dto.MovieDto;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public MovieDto getMovie(int movieId) throws MovieApiServiceException {
        return new MockMovieApiService().getMovie(movieId);
    }


}
