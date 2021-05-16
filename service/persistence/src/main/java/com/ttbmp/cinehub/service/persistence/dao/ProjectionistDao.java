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
            "WHERE " +
            "proiezione.id = :id AND " +
            "proiezione.id_sala = sala.id AND " +
            "sala.id = turno_proiezionista.sala_id AND " +
            "turno_proiezionista.turno_id = turno.id AND " +
            "turno.id_dipendente = dipendente.id_utente AND " +
            "proiezione.inizio BETWEEN turno.inizio AND turno.fine AND " +
            "turno.data = proiezione.data")
    Employee getProjectionistByProjectionId(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT  dipendente.* FROM cinemadb.dipendente, cinemadb.turno WHERE dipendente.id_utente = turno.id_dipendente AND turno.id = :id ")
    Employee getProjectionistByProjectionistShift(@Parameter(name = "id") int id) throws DaoMethodException;

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
