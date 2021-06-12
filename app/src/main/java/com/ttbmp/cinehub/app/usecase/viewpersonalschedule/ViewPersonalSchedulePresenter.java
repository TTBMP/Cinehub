package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ShiftListReply;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(ShiftListReply result);

    void presentGetProjectionList(ProjectionListReply result);

    void presentInvalidRequest(Request request);

    void presentNullRequest();

    void presentRepositoryError(RepositoryException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
