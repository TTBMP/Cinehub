package com.ttbmp.cinehub.core.repository;
/**
 * @author Palmieri Ivan
 */

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;

public interface CinemaRepository {

    List<Cinema> getCinemaByMovie(Movie movie);

    List<Cinema> getAllCinemaList();


    List<Cinema> getCinemaByHall(Hall hall);

    List<Cinema> retriveCinemaByProjection(Projection projection);


    List<Cinema> getCinemaByProjection(List<Projection> projection);
}
