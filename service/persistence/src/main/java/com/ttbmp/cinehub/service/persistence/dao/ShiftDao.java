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
            "FROM turno, dipendente " +
            "WHERE " +
            "dipendente.id_cinema = :idCinema AND " +
            "dipendente.id_utente = turno.id_dipendente AND " +
            "turno.data BETWEEN :start AND :end")
    List<Shift> getCinemaShiftListBetween(
            @Parameter(name = "idCinema") int idCinema,
            @Parameter(name = "start") @NotNull String start,
            @Parameter(name = "end") @NotNull String end
    ) throws DaoMethodException;

    @Query("SELECT turno.* FROM cinemadb.turno WHERE turno.id = :id")
    Shift getShiftById(@Parameter(name = "id") int name) throws DaoMethodException;

    @Query("SELECT turno.* FROM cinemadb.turno WHERE turno.id_dipendente = :idEmployee")
    List<Shift> getShiftListByEmployeeId(@Parameter(name = "idEmployee") @NotNull String name) throws DaoMethodException;

    @Query("SELECT  turno.* " +
            "FROM dipendente, turno " +
            "WHERE " +
            "dipendente.id_utente = turno.id_dipendente AND " +
            "dipendente.id_utente = :idEmployee AND " +
            "turno.data BETWEEN :start AND :end")
    List<Shift> getShiftBetweenDate(
            @Parameter(name = "idEmployee") @NotNull String id,
            @Parameter(name = "start") @NotNull String start,
            @Parameter(name = "end") @NotNull String end
    ) throws DaoMethodException;

    @Query("SELECT turno.* FROM turno WHERE turno.id_dipendente = :employeeId AND data = :date AND inizio = :start AND fine = :end")
    Shift getShift(
            @Parameter(name = "employeeId") @NotNull String employeeId,
            @Parameter(name = "date") @NotNull String date,
            @Parameter(name = "start") @NotNull String start,
            @Parameter(name = "end") @NotNull String end
    ) throws DaoMethodException;

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

