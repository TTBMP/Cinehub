package com.ttbmp.cinehub.core.service.movie;

import com.ttbmp.cinehub.core.entity.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    void retrieveAllMovie() throws IOException;

    void retrieveMovieById(Integer id) throws IOException;

    void retrieveMovie(URL url) throws IOException;

    void printSpecificAttribute(String output);

    List<Movie> returnListMovie();
}
