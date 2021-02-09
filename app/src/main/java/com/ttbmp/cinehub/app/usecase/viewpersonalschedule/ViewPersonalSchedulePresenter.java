package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(GetShiftListReply result);

    void presentInvalidGetShiftListRequest(GetShiftListRequest request);

    void presentAuthenticationError(Throwable error);

    void presentShiftListError(Throwable error);

    void presentGetShiftListNullRequest();

    void presentGetProjectionList(GetShiftProjectionListReply getShiftProjectionListReply);

}
