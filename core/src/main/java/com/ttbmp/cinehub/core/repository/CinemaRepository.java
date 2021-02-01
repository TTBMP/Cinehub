package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;

import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface CinemaRepository {


    List<Cinema> getAllCinemaList();

    List<Cinema> getCinema(Movie movie, String date);

}
