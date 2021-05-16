package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistShiftDao {

    @Query("SELECT * FROM turno_proiezionista WHERE turno_proiezionista.turno_id = :id")
    ProjectionistShift getProjectionistShiftByShiftId(@Parameter(name = "id") int id) throws DaoMethodException;

    @Insert
    void insert(ProjectionistShift shift) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<ProjectionistShift> shift) throws DaoMethodException;

    @Update
    void update(@NotNull ProjectionistShift shift) throws DaoMethodException;

    @Update
    void update(@NotNull List<ProjectionistShift> shift) throws DaoMethodException;

    @Delete
    void delete(@NotNull ProjectionistShift shift) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<ProjectionistShift> shift) throws DaoMethodException;
}
