package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.app.dto.MovieDto;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    MovieDto getMovie(int movieId) throws IOException;

}
