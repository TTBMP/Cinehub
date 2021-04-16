package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public interface CinemaRepository {

    List<Cinema> getAllCinema();

    List<Cinema> getListCinema(Integer movieId, String date);

    Cinema getCinema(Hall hall);

    Cinema getCinema(Projection projection);

    Cinema getCinema(Employee employee);

     Cinema getCinema(int cinemaId);

}
