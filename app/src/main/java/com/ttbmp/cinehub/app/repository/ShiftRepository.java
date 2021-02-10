package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.app.ShiftSaveException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.app.utilities.result.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    Result<List<Shift>> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

    Result<List<Shift>> getShiftList(int cinema);

    void saveShift(Shift shift) throws ShiftSaveException;

    void deletedShift(Shift shift) throws ShiftSaveException;

    void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException;

}