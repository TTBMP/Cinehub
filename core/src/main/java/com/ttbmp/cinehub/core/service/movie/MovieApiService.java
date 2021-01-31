package com.ttbmp.cinehub.core.service.movie;

import com.ttbmp.cinehub.core.dto.MovieDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieApiService {

    List<MovieDto> getAllMovie() throws IOException;

}
