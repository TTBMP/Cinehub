package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    Shift getShift(int shiftId);

    List<Shift> getShiftList();

    List<Shift> getShiftList(Employee employee);

    void saveShift(Shift shift);

    void deletedShift(Shift shift) throws ShiftSaveException;

    void modifyShift(Shift shift) throws ShiftSaveException;

    List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

}
