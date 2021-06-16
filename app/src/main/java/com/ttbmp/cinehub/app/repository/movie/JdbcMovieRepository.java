package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.service.persistence.dao.MovieDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcMovieRepository extends JdbcRepository implements MovieRepository {

    public JdbcMovieRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Movie getMovie(Integer movieId) {
        return new MovieProxy(getServiceLocator(), movieId);
    }

    @Override
    public Movie getMovie(Projection projection) throws RepositoryException {
        try {
            var movie = getDao(MovieDao.class).getMovieByProjection(projection.getId());
            return new MovieProxy(getServiceLocator(), movie.getId());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Movie> getMovieList(String localDate) throws RepositoryException {
        try {
            var movieList = getDao(MovieDao.class).getMovieByData(localDate);
            return movieList.stream()
                    .map(movie -> new MovieProxy(getServiceLocator(), movie.getId()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
