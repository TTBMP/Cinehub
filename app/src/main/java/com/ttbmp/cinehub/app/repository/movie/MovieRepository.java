package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public interface MovieRepository {

    Movie getMovie(Integer movieId) throws RepositoryException;

    Movie getMovie(Projection projection) throws RepositoryException;

    List<Movie> getAllMovie() throws RepositoryException;

    List<Movie> getMovieList(String localDate) throws RepositoryException;

}
