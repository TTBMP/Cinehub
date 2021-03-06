package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM dipendente WHERE dipendente.id_utente = :id")
    Employee getEmployeeById(@Parameter(name = "id") @NotNull String id) throws DaoMethodException;

    @Query("SELECT * FROM dipendente WHERE dipendente.id_utente IN " +
            "(SELECT turno.id_dipendente FROM turno WHERE turno.id = :id)")
    Employee getEmployeeByShiftId(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM dipendente")
    List<Employee> getAllEmployee() throws DaoMethodException;

    @Query("SELECT * FROM dipendente WHERE dipendente.id_cinema = :idCinema")
    List<Employee> getEmployeeList(@Parameter(name = "idCinema") int cinemaId) throws DaoMethodException;

}
