package com.ttbmp.cinehub.domain.usecase.manageemployeesshift;


import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.domain.utilities.result.Result;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter {

    void presentShiftList(Result<GetShiftListResponse> shiftList);

    void presentCinemaList(Result<GetCinemaListResponse> listCinema);

    void presentHallList(Result<GetHallListResponse> listHall);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(Result<ShiftRepeatResponse> response);

    void presentCreateShift(Result<CreateShiftResponse> response);

    void presentInvalidSaveShiftListRequest(ShiftRequest request);

    void presentSaveShiftNullRequest();

    void presentSaveShiftError(Throwable error);

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

    void presentInvalidHallListRequest(GetHallListRequest request);

    void presentHallListNullRequest();

}
