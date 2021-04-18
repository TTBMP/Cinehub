package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.io.IOException;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface MovieRepository {

    List<Movie> getMovieList(String localDate) throws IOException;

    Movie getMovie(Projection projection) throws IOException, DaoMethodException;

}
