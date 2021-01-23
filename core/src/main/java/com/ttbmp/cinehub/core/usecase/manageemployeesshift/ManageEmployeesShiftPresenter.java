package com.ttbmp.cinehub.core.usecase.manageemployeesshift;


import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetHallListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.ShiftResponse;
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

}
