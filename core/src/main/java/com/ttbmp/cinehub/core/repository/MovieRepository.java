package com.ttbmp.cinehub.core.repository;
/**
 * @author Palmieri Ivan
 */

import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;

import java.io.IOException;
import java.util.List;

public interface MovieRepository {


    List<Movie> getAllMovie();

    List<Movie> getAllMovieByApi(MovieApiService movieApiService) throws IOException;


}
