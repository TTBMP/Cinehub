package com.ttbmp.cinehub.core.usecase.manageemployeesshift;


import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.*;

/**
 * @author Massimo Mazzetti
 */

public interface ManageEmployeesShiftUseCase extends UseCase {

    void getShiftList(GetShiftListRequest request);

    boolean saveShift(ShiftRequest shift);

    void deleteShift(ShiftRequest shift);

    void getCinemaList();

    void getHallList(GetHallListRequest request);

    void saveRepeatedShift(ShiftRepeatRequest request);

    void createShift(CreateShiftRequest request);

}
