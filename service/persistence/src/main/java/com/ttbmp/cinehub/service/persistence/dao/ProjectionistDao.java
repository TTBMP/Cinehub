package com.ttbmp.cinehub.service.persistence.dao;


import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistDao {



    @Query("SELECT * FROM proiezionista WHERE proiezionista.id_dipendente in (" +
            "SELECT proiezione.id_proiezionista FROM proiezione WHERE proiezione.id = : id)")
    Employee getProjectionistByProjectionId(
            @Parameter(name = "id") @NotNull Integer id
    ) throws DaoMethodException;

    @Query("SELECT * FROM proiezionista WHERE proiezionista.id_dipendente in (" +
            " SELECT turno.id_dipendente FROM turno WHERE turno.id in (" +
            "SELECT turno_proiezionista.turno_id FROM turno_proiezionista WHERE turno_proiezionista.turno_id = :id ))  ")
    Employee getProjectionistByProjectionistShift(
            @Parameter(name = "id") @NotNull int id
    ) throws DaoMethodException;

    @Insert
    void insert(@NotNull Employee employee) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Employee> employee) throws DaoMethodException;

    @Update
    void update(@NotNull Employee employee) throws DaoMethodException;

    @Update
    void update(@NotNull List<Employee> employee) throws DaoMethodException;

    @Delete
    void delete(@NotNull Employee employee) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Employee> employee) throws DaoMethodException;
}
