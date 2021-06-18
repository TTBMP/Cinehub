package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM film WHERE film.id = :movieId")
    Movie getMovieById(@Parameter(name = "movieId") int id) throws DaoMethodException;

    @Query("SELECT * FROM film WHERE film.id IN " +
            "(SELECT proiezione.id_film FROM proiezione WHERE proiezione.id = :projectionId)")
    Movie getMovieByProjection(@Parameter(name = "projectionId") int projectionId) throws DaoMethodException;

    @Query("SELECT * FROM film")
    List<Movie> getAllMovie() throws DaoMethodException;

    @Query("SELECT * FROM film WHERE film.id IN " +
            "(SELECT proiezione.id_film FROM proiezione WHERE proiezione.data = :data)")
    List<Movie> getMovieByData(@Parameter(name = "data") @NotNull String name) throws DaoMethodException;

}
