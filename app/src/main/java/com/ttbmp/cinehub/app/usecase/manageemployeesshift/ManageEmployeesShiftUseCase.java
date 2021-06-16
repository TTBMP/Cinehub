package com.ttbmp.cinehub.app.usecase.manageemployeesshift;


import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftUseCase extends UseCase {

    void getCinemaList(GetCinemaListRequest request);

    void getEmployeeList(GetEmployeeListRequest request);

    void getShiftList(GetShiftListRequest request);

    void createRepeatedShift(ShiftRepeatRequest request);

    void deleteShift(ShiftRequest request);

    void createShift(CreateShiftRequest request);

    void modifyShift(ShiftModifyRequest request);

}
