package com.ttbmp.cinehub.core.service.movie;

import com.ttbmp.cinehub.core.entity.Movie;

import java.net.URL;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    void retriveAllMovie();

    void retriveMovieById(Integer id);

    void retriveMovie(URL url);

    void printSpecificAttribute(String output);

    List<Movie> returnListMovie();
}
