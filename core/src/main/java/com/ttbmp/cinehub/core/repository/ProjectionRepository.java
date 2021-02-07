package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {

    List<Projection> getProjectionList(CinemaDto mapToDto, MovieDto mapToDto1, String date);

    List<Projection> getProjectionList(String localDate);

    List<Projection> getProjectionList(Movie movie, String date);
}
