package com.ttbmp.cinehub.service.movieinformation.mock;


import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;


import java.net.URL;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public void retriveAllMovie() {

    }

    @Override
    public void retriveMovieById(Integer id) {

    }

    @Override
    public void retriveMovie(URL url) {

    }

    @Override
    public void printSpecificAttribute(String output) {

    }

    @Override
    public List<Movie> returnListMovie() {
        return null;
    }
}
