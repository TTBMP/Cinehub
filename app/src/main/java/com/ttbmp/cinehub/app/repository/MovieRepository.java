package com.ttbmp.cinehub.app.repository;


import com.ttbmp.cinehub.domain.Movie;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {

    List<Movie> getMovieList(String localDate) throws IOException;

}
