package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface ShiftDao {

    @Query("SELECT turno.* " +
            "FROM cinemadb.turno, cinemadb.dipendente " +
            "where dipendente.id_cinema = :idCinema " +
            "and dipendente.id_utente = turno.id_dipendente " +
            "and turno.data between :start and :end;")
    List<Shift> getCinemaShiftListBetween(
            @Parameter(name = "idCinema") @NotNull Integer idCinema,
            @Parameter(name = "start") @NotNull String start,
            @Parameter(name = "end") @NotNull String end
    ) throws DaoMethodException;

    @Query("SELECT turno.* from cinemadb.turno where turno.id = :id ")
    Shift getShiftById(
            @Parameter(name = "id") @NotNull Integer name
    ) throws DaoMethodException;

    @Query("SELECT turno.* from cinemadb.turno where turno.id_dipendente = :idEmployee ")
    List<Shift> getShiftListByEmployeeId(
            @Parameter(name = "idEmployee") @NotNull String name
    ) throws DaoMethodException;

    @Query("SELECT  turno.*  " +
            "from cinemadb.dipendente, cinemadb.turno " +
            "where dipendente.id_utente = turno.id_dipendente " +
            "and dipendente.id_utente = turno.id_dipendente " +
            "and dipendente.id_utente = :idEmployee " +
            "and turno.data between :start  and  :end ")
    List<Shift> getShiftBetweenDate(
            @Parameter(name = "idEmployee") @NotNull String id,
            @Parameter(name = "start") String start,
            @Parameter(name = "end") String end
    ) throws DaoMethodException;

    @Query("SELECT turno.* from cinemadb.turno where turno.id_dipendente = :employeeId and data = :date and inizio = :start and fine = :end")
    Shift getShift(@Parameter(name = "employeeId") @NotNull String employeeId,
                   @Parameter(name = "date") @NotNull String date,
                   @Parameter(name = "start") @NotNull String start,
                   @Parameter(name = "end") @NotNull String end) throws DaoMethodException;

    @Insert
    void insert(@NotNull Shift shift) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Shift> shift) throws DaoMethodException;

    @Update
    void update(@NotNull Shift shift) throws DaoMethodException;

    @Update
    void update(@NotNull List<Shift> shift) throws DaoMethodException;

    @Delete
    void delete(@NotNull Shift shift) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Shift> shift) throws DaoMethodException;


}

