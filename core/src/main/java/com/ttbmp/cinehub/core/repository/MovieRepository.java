package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {



    List<Movie> getMovieList(List<Projection> projectionList, List<Movie> movieList);
}
