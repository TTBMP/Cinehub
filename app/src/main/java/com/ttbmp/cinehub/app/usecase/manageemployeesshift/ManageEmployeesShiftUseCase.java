package com.ttbmp.cinehub.app.usecase.manageemployeesshift;


import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftUseCase extends UseCase {

    void getCinemaList();

    void getHallList(GetHallListRequest request);

    void getShiftList(GetShiftListRequest request);

    void saveRepeatedShift(ShiftRepeatRequest request);

    void deleteShift(ShiftRequest request);

    void createShift(CreateShiftRequest request);

    void saveShift(ShiftRequest request);

    void modifyShift(ShiftModifyRequest request);

}