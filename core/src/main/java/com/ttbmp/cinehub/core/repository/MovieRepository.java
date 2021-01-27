package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;

import java.io.IOException;
import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {


    List<MovieDto> getAllMovie(MovieApiService movieApiService) throws IOException;


}
