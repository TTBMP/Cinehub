package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;

public interface ProjectionRepository {

    List<Projection> getProjectionByHall(Hall hall);

    List<Projection> getProjectionByMovie(Movie movie, String data);

    List<Projection> getAllProjection();

    List<String> getTimeOfProjectionByMovieEndCinema(Movie movie, Cinema cinema);

    Projection getTimeOfProjectionByMovieEndCinemaEndTime(Movie movie, Cinema cinema, String time);
}
