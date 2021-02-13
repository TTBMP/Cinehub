package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    List<Shift> getShiftList();

    List<Shift> getShiftList(Employee employee);

    void saveShift(Shift shift) throws ShiftSaveException;

    void deletedShift(Shift shift) throws ShiftSaveException;

    void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException; // TODO pass as a parameter only the shift to edit

    List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

}
