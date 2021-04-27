package com.ttbmp.cinehub.app.usecase.manageemployeesshift;


import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.CreateShiftResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.ShiftRepeatResponse;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter {

    void presentShiftList(GetShiftListResponse shiftList);

    void presentCinemaList(GetCinemaListResponse listCinema);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(ShiftRepeatResponse response);

    void presentCreateShift(CreateShiftResponse response);

    void presentCreateShiftError(Throwable error);

    void presentInvalidDeleteShiftListRequest(ShiftRequest request);

    void presentDeleteShiftNullRequest();

    void presentDeleteShiftError(Throwable error);

    void presentInvalidModifyShiftListRequest(ShiftModifyRequest request);

    void presentModifyShiftNullRequest();

    void presentModifyShiftError(Throwable error);

    void presentInvalidCreateShiftListRequest(CreateShiftRequest request);

    void presentCreateShiftNullRequest();

    void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request);

    void presentRepeatedShiftNullRequest();

    void presentInvalidGetShiftListRequest(GetShiftListRequest request);

    void presentGetShiftListNullRequest();


}
