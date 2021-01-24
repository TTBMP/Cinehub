package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.repository.MovieRepository;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieRepository implements MovieRepository {

    /*Query al DB di darmi una lista di Movie mediante la DAOMovie*/
    @Override
    public List<Movie> getAllMovie() {
        return Arrays.asList(
                new Movie("Il mare"),
                new Movie("Franvx"),
                new Movie("Il cielo"),
                new Movie("Il film")
        );
    }

    @Override
    public List<Movie> getAllMovieByApi(MovieApiService movieApiService) throws IOException {
        movieApiService.retrieveAllMovie();
        return movieApiService.returnListMovie();
    }


}
