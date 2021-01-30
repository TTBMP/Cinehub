package com.ttbmp.cinehub.core.usecase.manageemployeesshift;


import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Massimo Mazzetti
 */

public interface ManageEmployeesShiftPresenter {

    void presentShiftList(Result<GetShiftListResponse> shiftList);

    void presentCinemaList(Result<GetCinemaListResponse> listCinema);

    void presentHallList(Result<GetHallListResponse> listHall);

    void presentSaveShift(Result<ShiftResponse> savedShift);

    void presentDeleteShift(Result<ShiftResponse> deleteShift);

    void presentRepeatShift(Result<ShiftRepeatResponse> response);

    void presentCreateShift(Result<CreateShiftResponse> response);

}
