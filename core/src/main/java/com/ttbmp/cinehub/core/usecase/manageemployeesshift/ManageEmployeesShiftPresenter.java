package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetHallListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftResponse;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.util.List;

public interface ManageEmployeesShiftPresenter {

    void presentShiftList(Result<GetShiftListResponse> shiftList);

    void presentCinemaList(Result<GetCinemaListResponse> listCinema);

    void presentHallList(Result<GetHallListResponse> listHall);

    void presentSaveShift(Result<GetShiftResponse> savedShift);

    void presentDeleteShift(Result<GetShiftResponse> deleteShift);

}
