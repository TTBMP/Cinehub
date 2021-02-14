package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM cinema")
    List<Movie> getAllMovie() throws DaoMethodException;

    @Query("SELECT DISTINCT film.id " +
            "FROM film, proiezione" +
            " WHERE film.id = proiezione.id_film AND proiezione.id_film = :date")
    List<Movie> getMovieList(Time date) throws IOException;

    @Query("SELECT DISTINCT film.id " +
            "FROM film, proiezione" +
            " WHERE film.id = proiezione.id_film AND proiezione.id_film = :projectionId")
    Movie getMovie(int projectionId);

    @Insert
    void insert(@NotNull Movie movie) throws DaoMethodException;

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
