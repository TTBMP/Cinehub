package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.repository.MovieRepository;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieRepository implements MovieRepository {


    @Override
    public List<Movie> getAllMovie(MovieApiService movieApiService) throws IOException {
        movieApiService.retrieveAllMovie();
        return movieApiService.returnListMovie();
    }


}
