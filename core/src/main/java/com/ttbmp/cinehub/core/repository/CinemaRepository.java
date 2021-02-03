package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */

public interface CinemaRepository {

    List<Cinema> getAllCinema();


    List<Cinema> getListCinema(Movie movie, String date);

    Cinema getCinema(Hall hall);

}
