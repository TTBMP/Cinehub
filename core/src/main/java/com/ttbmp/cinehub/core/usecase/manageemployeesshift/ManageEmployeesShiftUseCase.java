package com.ttbmp.cinehub.core.usecase.manageemployeesshift;


import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.*;

/**
 * @author Massimo Mazzetti
 */

public interface ManageEmployeesShiftUseCase extends UseCase {

    void getCinemaList();

    void getHallList(GetHallListRequest request);

    void getShiftList(GetShiftListRequest request);

    void saveRepeatedShift(ShiftRepeatRequest request);

    void deleteShift(ShiftRequest shift);

    void createShift(CreateShiftRequest request);

    void saveShift(ShiftRequest shift);

    void modifyShift(ShiftModifyRequest request);

}
