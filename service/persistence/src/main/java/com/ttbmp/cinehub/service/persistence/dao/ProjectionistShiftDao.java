package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistShiftDao {


    @Query("SELECT  turno.*  " +
            "from proiezione ,sala , turno_proiezionista , dipendente, turno   " +
            "where dipendente.id_utente = turno.id_dipendente  " +
            "and turno.id = :id " +
            "and proiezione.id_sala = sala.id  " +
            "and sala.id = turno_proiezionista.sala_id  " +
            "and turno_proiezionista.turno_id = turno.id  " +
            "and dipendente.id_utente = turno.id_dipendente " +
            "and proiezione.inizio between turno.inizio and turno.fine " +
            "and turno.data = proiezione.data  " +
            "and dipendente.ruolo = 'proiezionista'")
    Shift getProjectionistShiftByShiftId(
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
