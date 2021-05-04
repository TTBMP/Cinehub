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
    Cinema getCinemaById(
            @Parameter(name = "id") @NotNull Integer id
    ) throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id in (" +
            " SELECT dipendente.id_cinema FROM dipendente WHERE dipendente.id_utente = :id) ")
    Cinema getCinemaByEmployee(
            @Parameter(name = "id") @NotNull String id
    ) throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE cinema.id in ( " +
            "SELECT sala.id_cinema FROM sala WHERE sala.id in ( " +
            "SELECT proiezione.id_sala FROM proiezione WHERE proiezione.id = :id)) ")
    Cinema getCinemaByProjection(
            @Parameter(name = "id") @NotNull Integer projectionId
    ) throws DaoMethodException;

    @Query("SELECT * FROM cinema " +
            "WHERE cinema.id in (" +
            "SELECT sala.id_cinema " +
            "FROM sala " +
            "WHERE sala.id in (" +
            "SELECT proiezione.id_sala " +
            "FROM proiezione " +
            "WHERE proiezione.id_film = :movieId AND proiezione.data = :data))")
    List<Cinema> getCinemaByMovieIdAndDate(
            @Parameter(name = "movieId") @NotNull Integer movieId,
            @Parameter(name = "data") @NotNull String data
    ) throws DaoMethodException;


    @Query("SELECT * FROM cinema WHERE nome = :n")
    Cinema getCinema(
            @Parameter(name = "n") @NotNull String name
    ) throws DaoMethodException; //TODO: serve solo per test


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
