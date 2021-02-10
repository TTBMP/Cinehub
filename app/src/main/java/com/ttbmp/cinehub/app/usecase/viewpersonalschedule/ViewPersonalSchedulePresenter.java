package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(ShiftListReply result);

    void presentInvalidGetShiftListRequest(ShiftListRequest request);

    void presentAuthenticationError(Throwable error);

    void presentShiftListError(Throwable error);

    void presentGetShiftListNullRequest();

    void presentGetProjectionList(ProjectionListReply projectionListReply);

}
