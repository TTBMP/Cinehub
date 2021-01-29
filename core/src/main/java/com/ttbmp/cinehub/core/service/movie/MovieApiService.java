package com.ttbmp.cinehub.core.service.movie;

import com.ttbmp.cinehub.core.dto.MovieDto;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    void retriveAllMovie() throws IOException;

    void retrieveMovieById(Integer id) throws IOException;

    void retrieveMovie(URL url) throws IOException;

    void printSpecificAttribute(String output);

    List<MovieDto> returnListMovie();
}