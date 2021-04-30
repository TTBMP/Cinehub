package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public interface CinemaRepository {

    Cinema getCinema(int cinemaId) throws RepositoryException;

    Cinema getCinema(Employee employee) throws RepositoryException;

    Cinema getCinema(Projection projection) throws RepositoryException;

    List<Cinema> getAllCinema() throws RepositoryException;

    List<Cinema> getListCinema(Movie movie, String date) throws RepositoryException;

}
