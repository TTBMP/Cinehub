package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Projection;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
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

    @Query("SELECT * FROM proiezione, biglietto WHERE biglietto.id = :id AND proiezione.id = biglietto.id_proiezione")
    Projection getProjectionByTicketId(@Parameter(name = "id") int ticketId) throws DaoMethodException;

    @Query("SELECT * FROM proiezione WHERE proiezione.id_sala = :hallId AND proiezione.data = :date AND proiezione.inizio = :start")
    Projection getProjectionByDateAndTimeAndHallId(
            @Parameter(name = "date") @NotNull String date,
            @Parameter(name = "start") @NotNull String startTime,
            @Parameter(name = "hallId") int hallId
    ) throws DaoMethodException;

    @Query("SELECT * FROM proiezione")
    List<Projection> getAllProjection() throws DaoMethodException;

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
            "turno.id = turno_proiezionista.id_turno AND " +
            "turno_proiezionista.id_sala = proiezione.id_sala AND " +
            "proiezione.data = turno.data AND " +
            "proiezione.inizio BETWEEN turno.inizio AND turno.fine")
    List<Projection> getProjectionListByProjectionistShift(@Parameter(name = "id") int id) throws DaoMethodException;

}
