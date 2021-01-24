package com.ttbmp.cinehub.core.usecase.manageemployeesshift;


import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetHallListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.ShiftRequest;


/**
 * @author Massimo Mazzetti
 */

public interface ManageEmployeesShiftUseCase extends UseCase {

    void getShiftList(GetShiftListRequest request);

    boolean saveShift(ShiftRequest shift);

    void deleteShift(ShiftRequest shift);

    void getCinemaList();

    void getHallList(GetHallListRequest request);

}
