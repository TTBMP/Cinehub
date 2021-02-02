package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface CinemaRepository {


    List<Cinema> getAllCinemaList();

    List<Cinema> getListCinema(Movie movie, String date);

    List<Cinema> getListCinema(List<Projection> projectionList);
}
