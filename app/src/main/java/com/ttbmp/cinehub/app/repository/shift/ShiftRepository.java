package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

    List<Shift> getShiftList(int cinemaId);

    void saveShift(Shift shift) throws ShiftSaveException;

    void deletedShift(Shift shift) throws ShiftSaveException;

    void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException;

}