package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.entity.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistShiftDao {

    @Query("SELECT * from turno_proiezionista where turno_proiezionista.turno_id = :id")
    ProjectionistShift getProjectionistShiftByShiftId(
            @Parameter(name = "id") @NotNull Integer id
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
