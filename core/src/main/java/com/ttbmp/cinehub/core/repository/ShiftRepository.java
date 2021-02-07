package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.ShiftSaveException;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.shift.Shift;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    Result<List<Shift>> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

    Result<List<Shift>> getShiftList();

    void saveShift(Shift shift) throws ShiftSaveException;

    void deletedShift(Shift shift) throws ShiftSaveException;

    void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException;

}