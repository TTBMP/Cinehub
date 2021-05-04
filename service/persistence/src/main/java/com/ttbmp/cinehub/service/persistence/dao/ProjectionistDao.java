package com.ttbmp.cinehub.service.persistence.dao;


import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistDao {


    @Query("SELECT dipendente.* " +
            "FROM cinemadb.dipendente, cinemadb.proiezione, cinemadb.turno_proiezionista, cinemadb.sala, cinemadb.turno " +
            "WHERE proiezione.id = :id " +
            "and proiezione.id_sala = sala.id " +
            "and sala.id = turno_proiezionista.sala_id " +
            "and turno_proiezionista.turno_id = turno.id " +
            "and turno.id_dipendente = dipendente.id_utente " +
            "and proiezione.inizio between turno.inizio and turno.fine " +
            "and turno.data = proiezione.data;")
    Employee getProjectionistByProjectionId(
            @Parameter(name = "id") @NotNull int id
    ) throws DaoMethodException;

    @Query("SELECT  dipendente.* from cinemadb.dipendente, cinemadb.turno where dipendente.id_utente = turno.id_dipendente and turno.id = :id ")
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
