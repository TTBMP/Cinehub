package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Projection;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface ProjectionDao {

    @Query("SELECT * FROM proiezione WHERE proiezione.id_film = :movieId AND " +
            " proiezione.data = :date AND ( proiezione.id_sala in ( " +
            " SELECT sala.id FROM sala WHERE sala.id_cinema = :cinemaId )) ")
    List<Projection> getProjectionListByCinemaAndMovieAndDate(
            @Parameter(name = "cinemaId") @NotNull Integer cinemaId,
            @Parameter(name = "movieId") @NotNull Integer movieId,
            @Parameter(name = "date") @NotNull String date
    ) throws DaoMethodException;


    @Query("SELECT * FROM proiezione WHERE proiezione.data = :date")
    List<Projection> getProjectionListByDate(
            @Parameter(name = "date") @NotNull String date
    ) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.id_film = :movieId AND proiezione.data = :date")
    List<Projection> getProjectionListByDateAndMovie(
            @Parameter(name = "movieId") @NotNull Integer movieId,
            @Parameter(name = "date") @NotNull String date
    ) throws DaoMethodException;


    @Query("SELECT distinct proiezione.* " +
            "FROM proiezione " +
            "WHERE id_proiezionista= :id ")
    List<Projection> getProjectionListByProjectionist(
            @Parameter(name = "id") @NotNull String id
    ) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.id_sala = :hallId AND proiezione.data = :date AND proiezione.inizio = :startingTime")
    Projection getProjectionByDateAndTimeAndHallId(
            @Parameter(name = "date") @NotNull String date,
            @Parameter(name = "startingTime") @NotNull String startingTime,
            @Parameter(name = "hallId") @NotNull int hallId
    ) throws DaoMethodException;

    @Insert
    void insert(@NotNull Projection projection) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Projection> projection) throws DaoMethodException;

    @Update
    void update(@NotNull Projection projection) throws DaoMethodException;

    @Update
    void update(@NotNull List<Projection> projection) throws DaoMethodException;

    @Delete
    void delete(@NotNull Projection projection) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Projection> projection) throws DaoMethodException;

}
