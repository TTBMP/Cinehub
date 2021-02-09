package com.ttbmp.cinehub.app.service.movieapi;


import com.ttbmp.cinehub.app.dto.MovieDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public List<MovieDto> getAllMovie() throws IOException {
        return new ArrayList<>();
    }

    @Override
    public MovieDto getMovie(int movieId) throws IOException {
        return null;
    }


}
