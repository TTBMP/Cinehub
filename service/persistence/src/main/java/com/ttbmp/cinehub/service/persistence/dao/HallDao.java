package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Hall;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface HallDao {

    @Query("SELECT * FROM sala WHERE sala.id = :id")
    Hall getHallById(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id_cinema = :cinemaId")
    List<Hall> getHallByCinemaId(@Parameter(name = "cinemaId") int id) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id IN " +
            "(SELECT proiezione.id_sala FROM proiezione WHERE proiezione.id = :projectionId)")
    Hall getHallByProjectionId(@Parameter(name = "projectionId") int projectionId) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id IN " +
            "(SELECT turno_proiezionista.id_sala FROM turno_proiezionista WHERE turno_proiezionista.id_turno = :id)")
    Hall getHallByProjectionistShift(@Parameter(name = "id") int projectionistShiftId) throws DaoMethodException;

    @Insert
    void insert(@NotNull Hall hall) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Hall> hall) throws DaoMethodException;

    @Update
    void update(@NotNull Hall hall) throws DaoMethodException;

    @Update
    void update(@NotNull List<Hall> hall) throws DaoMethodException;

    @Delete
    void delete(@NotNull Hall hall) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Hall> hall) throws DaoMethodException;

}
