package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    Shift getShift(int shiftId) throws RepositoryException;

    List<Shift> getShiftList() throws RepositoryException;

    List<Shift> getShiftList(Employee employee) throws RepositoryException;

    void saveShift(Shift shift) throws RepositoryException;

    void deletedShift(Shift shift) throws RepositoryException;

    void modifyShift(Shift shift) throws RepositoryException;

    List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) throws RepositoryException;

}
