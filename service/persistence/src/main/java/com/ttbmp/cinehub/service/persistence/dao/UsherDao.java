package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface UsherDao {

    @Query("SELECT  distinct dipendente.*  " +
            "from cinemadb.proiezione ,cinemadb.sala , cinemadb.dipendente, cinemadb.turno   " +
            "where dipendente.id_utente = turno.id_dipendente " +
            "and turno.id = :idShift " +
            "and proiezione.id_sala = sala.id " +
            "and dipendente.id_utente = turno.id_dipendente " +
            "and proiezione.inizio between turno.inizio and turno.fine " +
            "and turno.data = proiezione.data  " +
            "and dipendente.ruolo = 'maschera'")
    Employee getUsherByUsherShift(
            @Parameter(name = "idShift") @NotNull int idShift
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
