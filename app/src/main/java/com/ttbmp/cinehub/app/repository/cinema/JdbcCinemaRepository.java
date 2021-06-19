package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.service.persistence.dao.CinemaDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcCinemaRepository extends JdbcRepository implements CinemaRepository {

    public JdbcCinemaRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Cinema getCinema(int cinemaId) throws RepositoryException {
        try {
            var cinema = getDao(CinemaDao.class).getCinemaById(cinemaId);
            return new CinemaProxy(
                    getServiceLocator(),
                    cinema.getId(),
                    cinema.getName(),
                    cinema.getCity(),
                    cinema.getAddress()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Cinema getCinema(Employee employee) throws RepositoryException {
        try {
            var cinema = getDao(CinemaDao.class).getCinemaByEmployee(employee.getId());
            return new CinemaProxy(
                    getServiceLocator(),
                    cinema.getId(),
                    cinema.getName(),
                    cinema.getCity(),
                    cinema.getAddress()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Cinema getCinema(Projection projection) throws RepositoryException {
        try {
            var cinema = getDao(CinemaDao.class).getCinemaByProjection(projection.getId());
            return new CinemaProxy(
                    getServiceLocator(),
                    cinema.getId(),
                    cinema.getName(),
                    cinema.getCity(),
                    cinema.getAddress()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Cinema> getAllCinema() throws RepositoryException {
        try {
            var cinemaList = getDao(CinemaDao.class).getAllCinema();
            return cinemaList.stream()
                    .map(cinema -> new CinemaProxy(
                            getServiceLocator(),
                            cinema.getId(),
                            cinema.getName(),
                            cinema.getCity(),
                            cinema.getAddress()
                    ))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) throws RepositoryException {
        try {
            var cinemaList = getDao(CinemaDao.class).getCinemaByMovieIdAndDate(movie.getId(), date);
            return cinemaList.stream()
                    .map(cinema -> new CinemaProxy(
                            getServiceLocator(),
                            cinema.getId(),
                            cinema.getName(),
                            cinema.getCity(),
                            cinema.getAddress()
                    ))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
