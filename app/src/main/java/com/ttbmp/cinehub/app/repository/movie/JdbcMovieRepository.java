package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.MovieDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcMovieRepository implements MovieRepository {

    private final ServiceLocator serviceLocator;

    private MovieDao movieDao = null;

    public JdbcMovieRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;

    }

    @Override
    public List<Movie> getMovieList(String localDate) throws IOException {
        List<com.ttbmp.cinehub.service.persistence.entity.Movie> movieList = getMovieDao().getMovieList(Time.valueOf(localDate));
        return movieList.stream()
                .map(movie -> new MovieProxy(movie.getId(), serviceLocator.getService(MovieApiService.class)))
                .collect(Collectors.toList());
    }

    @Override
    public Movie getMovie(Projection projection) throws IOException, DaoMethodException {
        com.ttbmp.cinehub.service.persistence.entity.Movie movie = getMovieDao().getMovieByProjection(projection.getId());
        return new MovieProxy(movie.getId(), serviceLocator.getService(MovieApiService.class));
    }

    private MovieDao getMovieDao() {
        if (movieDao == null) {
            try {
                this.movieDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getMovieDao();
            } catch (Exception e) {
                e.printStackTrace();
                //throw new RepositoryException(e.getMessage());
            }


        }
        return movieDao;
    }

}
