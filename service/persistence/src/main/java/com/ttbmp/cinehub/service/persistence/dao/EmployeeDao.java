package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM dipendente WHERE dipendente.id_utente = :id;")
    Employee getEmployeeById(
            @Parameter(name = "id") @NotNull String id
    ) throws DaoMethodException;

    @Query("SELECT * FROM dipendente WHERE dipendente.id_utente in (" +
            "SELECT turno.id_dipendente FROM turno WHERE turno.id = :id) ")
    Employee getEmployeeByShiftId(
            @Parameter(name = "id") @NotNull int id
    ) throws DaoMethodException;

    @Query("SELECT * FROM dipendente WHERE dipendente.id_cinema = :idCinema ")
    List<Employee> getEmployeeList(
            @Parameter(name = "idCinema") @NotNull int cinemaId
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
