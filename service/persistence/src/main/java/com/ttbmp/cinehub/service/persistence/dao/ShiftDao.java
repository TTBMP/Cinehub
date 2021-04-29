package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.entity.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface ShiftDao {

    @Query("SELECT * FROM turno")
    List<Shift> getShiftList(
    ) throws DaoMethodException;

    @Query("SELECT * FROM turno where id = :id;")
    Shift getShiftById(
            @Parameter(name = "id") @NotNull Integer name
    ) throws DaoMethodException;

    @Query("SELECT * FROM turno where id_dipendente= :idEmployee")
    List<Shift> getShiftListByEmployeeId(
            @Parameter(name = "idEmployee") @NotNull String name
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

