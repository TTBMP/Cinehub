package com.ttbmp.cinehub.service.movieinformation.mock;


import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public void retrieveAllMovie() {
        //Empty section
    }

    @Override
    public void retrieveMovieById(Integer id) {
       //Empty section
    }

    @Override
    public void retrieveMovie(URL url) {
        //Empty section
    }

    @Override
    public void printSpecificAttribute(String output) {
        //Empty section
    }

    @Override
    public List<Movie> returnListMovie() {
        return new ArrayList<>();
    }
}
