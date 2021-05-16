package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Cinema;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface CinemaDao {

    @Query("SELECT * FROM cinema")
    List<Cinema> getAllCinema() throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id = :id")
    Cinema getCinemaById(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id IN " +
            "(SELECT dipendente.id_cinema FROM dipendente WHERE dipendente.id_utente = :id)")
    Cinema getCinemaByEmployee(@Parameter(name = "id") @NotNull String id) throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id IN " +
            "(SELECT sala.id_cinema FROM sala WHERE sala.id IN " +
            "(SELECT proiezione.id_sala FROM proiezione WHERE proiezione.id = :id))")
    Cinema getCinemaByProjection(@Parameter(name = "id") int projectionId) throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id IN " +
            "(SELECT sala.id_cinema FROM sala WHERE sala.id IN " +
            "(SELECT proiezione.id_sala FROM proiezione WHERE proiezione.id_film = :movieId AND proiezione.data = :data))")
    List<Cinema> getCinemaByMovieIdAndDate(
            @Parameter(name = "movieId") int movieId,
            @Parameter(name = "data") @NotNull String data
    ) throws DaoMethodException;

    @Insert
    void insert(@NotNull Cinema cinema) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Cinema> cinema) throws DaoMethodException;

    @Update
    void update(@NotNull Cinema cinema) throws DaoMethodException;

    @Update
    void update(@NotNull List<Cinema> cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull Cinema cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Cinema> cinema) throws DaoMethodException;

}
