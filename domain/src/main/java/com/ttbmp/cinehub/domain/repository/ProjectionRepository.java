package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.dto.CinemaDto;
import com.ttbmp.cinehub.domain.dto.MovieDto;
import com.ttbmp.cinehub.domain.entity.Movie;
import com.ttbmp.cinehub.domain.entity.Projection;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {

    List<Projection> getProjectionList(CinemaDto mapToDto, MovieDto mapToDto1, String date);

    List<Projection> getProjectionList(String localDate);

    List<Projection> getProjectionList(Movie movie, String date);
}
