package com.ttbmp.cinehub.service.movieinformation.mock;



import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    @Override
    public List<MovieDto> getAllMovie() throws IOException {
        return new ArrayList<>();
    }



}
