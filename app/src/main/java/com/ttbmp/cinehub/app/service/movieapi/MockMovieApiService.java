package com.ttbmp.cinehub.app.service.movieapi;


import com.ttbmp.cinehub.app.dto.MovieDto;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public MovieDto getMovie(int movieId) throws IOException {
        return new MockMovieApiService().getMovie(movieId);
    }


}
