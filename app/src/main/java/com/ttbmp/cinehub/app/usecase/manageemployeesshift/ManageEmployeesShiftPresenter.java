package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter {

    void presentShiftList(GetShiftListReply reply);

    void presentEmployeeList(GetEmployeeListReply reply);

    void presentCinemaList(GetCinemaListReply reply);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(ShiftRepeatReply reply);

    void presentCreateShift(CreateShiftReply reply);

    void presentCreateShiftError(Throwable error);

    void presentModifyShiftError(Throwable error);

    void presentInvalidRequest(Request request);

    void presentNullRequest();

    void presentRepositoryError(RepositoryException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

}
