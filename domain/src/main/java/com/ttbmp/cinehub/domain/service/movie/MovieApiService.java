package com.ttbmp.cinehub.domain.service.movie;

import com.ttbmp.cinehub.domain.dto.MovieDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    List<MovieDto> getAllMovie() throws IOException;

    MovieDto getMovie(int movieId) throws IOException;
}
