package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(GetShiftListResponse result);

    void presentInvalidGetShiftListRequest(GetShiftListRequest request);

    void presentAuthenticationError(Throwable error);

    void presentShiftListError(Throwable error);

    void presentGetShiftListNullRequest();

}
