package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

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

}
