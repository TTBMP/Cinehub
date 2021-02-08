package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.entity.Cinema;
import com.ttbmp.cinehub.domain.entity.Hall;
import com.ttbmp.cinehub.domain.entity.Movie;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public interface CinemaRepository {

    List<Cinema> getAllCinema();

    List<Cinema> getListCinema(Movie movie, String date);

    Cinema getCinema(Hall hall);

}
