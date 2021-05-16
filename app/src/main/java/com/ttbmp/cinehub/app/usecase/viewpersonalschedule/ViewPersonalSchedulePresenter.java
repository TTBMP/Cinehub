package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.security.SecurityException;

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

    void presentSecurityError(SecurityException e);

    void presentRepositoryError(RepositoryException e);

}
