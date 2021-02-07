package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Movie;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {

    List<Movie> getMovieList(String localDate) throws IOException;

}
