package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.CinemaDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcCinemaRepository implements CinemaRepository{

    private final ServiceLocator serviceLocator;

    private CinemaDao cinemaDao = null;

    public JdbcCinemaRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public List<Cinema> getAllCinema() throws RepositoryException {
        List<com.ttbmp.cinehub.service.persistence.entity.Cinema> cinemaList = null;
        try {
            cinemaList = getCinemaDao().getAllCinema();
            return cinemaList.stream()
                    .map(cinema -> new CinemaProxy(cinema.getId(), cinema.getName(),cinema.getCity(),cinema.getAddress(),serviceLocator.getService(HallRepository.class)))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
              throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Cinema> cinemaList = getCinemaDao().getCinemaByMovieIdAndDate(movie.getId(),date);
            return cinemaList.stream()
                    .map(cinema -> new CinemaProxy(cinema.getId(), cinema.getName(),cinema.getCity(),cinema.getAddress(),serviceLocator.getService(HallRepository.class)))
                    .collect(Collectors.toList());
        } catch (Throwable e) {
           return new ArrayList<>();
        }

    }


    @Override
    public Cinema getCinema(Projection projection) throws RepositoryException {
        try {
            com.ttbmp.cinehub.service.persistence.entity.Cinema cinema =  getCinemaDao().getCinemaByProjection(projection.getId());
            return new CinemaProxy(cinema.getId(), cinema.getName(),cinema.getCity(),cinema.getAddress(), serviceLocator.getService(HallRepository.class));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Cinema getCinema(Employee employee) throws RepositoryException {
        try {
            com.ttbmp.cinehub.service.persistence.entity.Cinema cinema =  getCinemaDao().getCinemaByEmployee(employee.getId());
            return new CinemaProxy(cinema.getId(), cinema.getName(),cinema.getCity(),cinema.getAddress(), serviceLocator.getService(HallRepository.class));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Cinema getCinema(int cinemaId) {
        return null;
    }

    private CinemaDao getCinemaDao() {
        if (cinemaDao == null) {
            try {
                this.cinemaDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cinemaDao;
    }
}
