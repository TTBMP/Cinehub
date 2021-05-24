package com.ttbmp.cinehub.app.usecase.manageemployeesshift;


import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter {

    void presentShiftList(GetShiftListResponse shiftList);

    void presentEmployeeList(GetEmployeeListResponse employeeList);

    void presentCinemaList(GetCinemaListResponse listCinema);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(ShiftRepeatResponse response);

    void presentCreateShift(CreateShiftResponse response);

    void presentInvalidGetCinemaListRequest(GetCinemaListRequest request);

    void presentCinemaListNullRequest();

    void presentCreateShiftError(Throwable error);

    void presentInvalidEmployeeListRequest(GetEmployeeListRequest request);

    void presentEmployeeListNullRequest();

    void presentInvalidDeleteShiftListRequest(ShiftRequest request);

    void presentDeleteShiftNullRequest();

    void presentInvalidModifyShiftListRequest(ShiftModifyRequest request);

    void presentModifyShiftNullRequest();

    void presentModifyShiftError(Throwable error);

    void presentInvalidCreateShiftListRequest(CreateShiftRequest request);

    void presentCreateShiftNullRequest();

    void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request);

    void presentRepeatedShiftNullRequest();

    void presentInvalidGetShiftListRequest(GetShiftListRequest request);

    void presentGetShiftListNullRequest();

    void presentRepositoryError(RepositoryException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

}
