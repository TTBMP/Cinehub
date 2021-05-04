package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface UsherDao {

    @Query("SELECT dipendente.*  " +
            "from cinemadb.dipendente, cinemadb.turno  " +
            " where dipendente.id_utente = turno.id_dipendente " +
            "and turno.id = :idShift ")
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
