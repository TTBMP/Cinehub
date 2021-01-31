package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {


    List<Projection> getAllProjection();

    Projection getASpecificProjection(MovieDto movie, CinemaDto cinema, String date, String time);
}
