package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
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

    @Query("SELECT * FROM film WHERE film.id IN " +
            "(SELECT proiezione.id_film FROM proiezione WHERE proiezione.data = :data)")
    List<Movie> getMovieByData(@Parameter(name = "data") @NotNull String name) throws DaoMethodException;

    @Insert
    void insert(@NotNull Movie movie);

    @Insert
    void insert(@NotNull List<Movie> movie) throws DaoMethodException;

    @Update
    void update(@NotNull Movie movie) throws DaoMethodException;

    @Update
    void update(@NotNull List<Movie> movie) throws DaoMethodException;

    @Delete
    void delete(@NotNull Movie movie) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Movie> movie) throws DaoMethodException;

}
