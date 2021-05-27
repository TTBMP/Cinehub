package com.ttbmp.cinehub.app.usecase.manageemployeesshift;


import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter {

    void presentShiftList(GetShiftListResponse response);

    void presentEmployeeList(GetEmployeeListResponse response);

    void presentCinemaList(GetCinemaListResponse response);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(ShiftRepeatResponse response);

    void presentCreateShift(CreateShiftResponse response);

    void presentCreateShiftError(Throwable error);

    void presentModifyShiftError(Throwable error);

    void presentNullRequest();

    void presentInvalidRequest(Request request);

    void presentRepositoryError(RepositoryException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

}
