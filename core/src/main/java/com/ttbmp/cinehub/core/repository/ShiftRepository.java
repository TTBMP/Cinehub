package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public interface ShiftRepository {

    Result<List<Shift>> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end);

    Result<List<Shift>> getShiftList();

    Result<Boolean> saveShift(Shift shift);

    Result<Shift> deletedShift(Shift shift);

}
