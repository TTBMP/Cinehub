package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public interface CinemaRepository {

    List<Cinema> getAllCinema() throws RepositoryException;

    List<Cinema> getListCinema(Movie movie, String date) throws  RepositoryException;

    Cinema getCinema(Projection projection) throws  RepositoryException;

    Cinema getCinema(Employee employee) throws RepositoryException;

    Cinema getCinema(int cinemaId);

}
