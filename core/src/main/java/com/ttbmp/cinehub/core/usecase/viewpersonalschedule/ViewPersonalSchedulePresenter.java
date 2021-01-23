package com.ttbmp.cinehub.core.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter {

    void presentGetShiftList(Result<GetShiftListResponse> result);

    void presentInvalidGetShiftListRequest(GetShiftListRequest request);

    void presentAuthenticationError(Throwable error);

    void presentShiftListError(Throwable error);

    void presentGetShiftListNullRequest();

}
