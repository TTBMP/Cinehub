package com.ttbmp.cinehub.service.persistence.dao;


import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistDao {


    @Query("SELECT * FROM cinemadb.dipendente WHERE dipendente.ruolo = 'proiezionista' AND dipendente.id_utente IN ( " +
            "SELECT turno.id_dipendente FROM cinemadb.turno WHERE turno.id IN ( " +
            "SELECT turno_proiezionista.turno_id FROM cinemadb.turno_proiezionista WHERE turno_proiezionista.sala_id IN ( " +
            "SELECT proiezione.id_sala FROM cinemadb.proiezione WHERE proiezione.id = :id ))); ")
    Employee getProjectionistByProjectionId(
            @Parameter(name = "id") @NotNull int id
    ) throws DaoMethodException;

    @Query("SELECT  dipendente.*  " +
            "from cinemadb.proiezione ,cinemadb.sala , cinemadb.turno_proiezionista , cinemadb.dipendente, cinemadb.turno   " +
            "where dipendente.id_utente = turno.id_dipendente " +
            "and turno.id = :id " +
            "and proiezione.id_sala = sala.id " +
            "and sala.id = turno_proiezionista.sala_id " +
            "and turno_proiezionista.turno_id = turno.id " +
            "and dipendente.id_utente = turno.id_dipendente " +
            "and proiezione.inizio between turno.inizio and turno.fine " +
            "and turno.data = proiezione.data  " +
            "and dipendente.ruolo = 'proiezionista' ")
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
