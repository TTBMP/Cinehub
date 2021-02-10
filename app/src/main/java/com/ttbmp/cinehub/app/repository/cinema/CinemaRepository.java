package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public interface CinemaRepository {

    List<Cinema> getAllCinema();

    List<Cinema> getListCinema(Movie movie, String date);

    Cinema getCinema(Hall hall);

}
