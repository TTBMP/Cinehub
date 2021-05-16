package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Projection;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Dao
public interface ProjectionDao {

    @Query("SELECT * FROM proiezione WHERE proiezione.id = :id")
    Projection getProjectionById(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.data = :date")
    List<Projection> getProjectionListByDate(@Parameter(name = "date") @NotNull String date) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE " +
            "proiezione.id_film = :movieId AND " +
            "proiezione.data = :date AND " +
            "proiezione.id_sala IN (SELECT sala.id FROM sala WHERE sala.id_cinema = :cinemaId)")
    List<Projection> getProjectionListByCinemaAndMovieAndDate(
            @Parameter(name = "cinemaId") int cinemaId,
            @Parameter(name = "movieId") int movieId,
            @Parameter(name = "date") @NotNull String date
    ) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.id_film = :movieId AND proiezione.data = :date")
    List<Projection> getProjectionListByDateAndMovie(
            @Parameter(name = "movieId") int movieId,
            @Parameter(name = "date") @NotNull String date
    ) throws DaoMethodException;

    @Query("SELECT proiezione.* " +
            "FROM cinemadb.turno , cinemadb.turno_proiezionista, cinemadb.proiezione " +
            "WHERE " +
            "turno.id = :id AND " +
            "turno.id = turno_proiezionista.turno_id AND " +
            "turno_proiezionista.sala_id = proiezione.id_sala AND " +
            "proiezione.data = turno.data AND " +
            "proiezione.inizio BETWEEN turno.inizio AND turno.fine")
    List<Projection> getProjectionListByProjectionistShift(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.id_sala = :hallId AND proiezione.data = :date AND proiezione.inizio = :start")
    Projection getProjectionByDateAndTimeAndHallId(
            @Parameter(name = "date") @NotNull String date,
            @Parameter(name = "start") @NotNull String startTime,
            @Parameter(name = "hallId") int hallId
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
