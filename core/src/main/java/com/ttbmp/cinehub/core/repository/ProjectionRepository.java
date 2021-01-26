package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {


    List<Projection> getProjectionByMovie(Movie movie, String data);

    List<Projection> getAllProjection();

    List<String> getTimeOfProjectionByMovieCinema(Movie movie, Cinema cinema);

    Projection getTimeOfProjectionByMovieCinemaTime(Movie movie, Cinema cinema, String time);
}
