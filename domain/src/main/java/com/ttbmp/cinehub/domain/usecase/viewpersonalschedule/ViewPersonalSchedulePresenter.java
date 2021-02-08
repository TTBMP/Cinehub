package com.ttbmp.cinehub.domain.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.domain.utilities.result.Result;

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
