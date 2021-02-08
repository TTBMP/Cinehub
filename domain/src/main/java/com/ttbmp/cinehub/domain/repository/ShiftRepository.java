package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.ShiftSaveException;
import com.ttbmp.cinehub.domain.entity.Employee;
import com.ttbmp.cinehub.domain.entity.shift.Shift;
import com.ttbmp.cinehub.domain.utilities.result.Result;

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