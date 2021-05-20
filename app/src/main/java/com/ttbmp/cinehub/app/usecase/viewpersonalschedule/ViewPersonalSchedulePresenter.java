package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(ShiftListReply result);

    void presentInvalidShiftListRequest(ShiftListRequest request);

    void presentShiftListNullRequest();

    void presentGetProjectionList(ProjectionListReply projectionListReply);

    void presentProjectionListNullRequest();

    void presentInvalidProjectionListRequest(ProjectionListRequest request);

    void presentRepositoryError(RepositoryException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
