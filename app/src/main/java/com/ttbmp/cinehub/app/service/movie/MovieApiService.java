package com.ttbmp.cinehub.app.service.movie;

import com.ttbmp.cinehub.app.dto.MovieDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    List<MovieDto> getAllMovie() throws IOException;

    MovieDto getMovie(int movieId) throws IOException;
}
