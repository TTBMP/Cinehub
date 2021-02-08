package com.ttbmp.cinehub.domain.repository;


import com.ttbmp.cinehub.domain.entity.Movie;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {

    List<Movie> getMovieList(String localDate) throws IOException;

}
