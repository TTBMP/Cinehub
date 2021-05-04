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

    @Query("SELECT * FROM sala WHERE sala.id = :id;")
    Hall getHallById(
            @Parameter(name = "id") @NotNull Integer id
    ) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id_cinema = :cinemaId;")
    List<Hall> getHallByCinemaId(
            @Parameter(name = "cinemaId") @NotNull Integer id
    ) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id in ( SELECT proiezione.id_sala FROM proiezione WHERE proiezione.id = :projectionId) ")
    Hall getHallByProjectionId(
            @Parameter(name = "projectionId") @NotNull Integer projectionId
    ) throws DaoMethodException;

    @Query("SELECT * FROM sala WHERE sala.id in (SELECT turno_proiezionista.sala_id FROM turno_proiezionista WHERE turno_proiezionista.turno_id = :projectionistShift)  ")
    Hall getHallByProjectionistShift(
            @Parameter(name = "projectionistShift") @NotNull Integer projectionistShift
    ) throws DaoMethodException;

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
