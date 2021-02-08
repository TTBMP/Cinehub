package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.core.usecase.viewpersonalschedule.GetShiftListRequest;
import com.ttbmp.cinehub.core.usecase.viewpersonalschedule.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.viewpersonalschedule.ViewPersonalSchedulePresenter;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleFxPresenter implements ViewPersonalSchedulePresenter {

    private final ViewPersonalScheduleViewModel viewModel;

    public ViewPersonalScheduleFxPresenter(ViewPersonalScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentGetShiftList(Result<GetShiftListResponse> result) {
        if (result.hasError()) {
            result.getError().printStackTrace();
        }
        viewModel.getShiftList().setAll(result.getValue().getShiftDtoList());
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_START_TIME_ERROR)) {
            System.out.println(GetShiftListRequest.MISSING_START_TIME_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_END_TIME_ERROR)) {
            System.out.println(GetShiftListRequest.MISSING_END_TIME_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.INVALID_TIME_SELECTION_ERROR)) {
            System.out.println(GetShiftListRequest.INVALID_TIME_SELECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentAuthenticationError(Throwable error) {
        System.out.println(error.getMessage());
    }

    @Override
    public void presentShiftListError(Throwable error) {
        System.out.println(error.getMessage());
    }

    @Override
    public void presentGetShiftListNullRequest() {
        System.out.println("Request can't be null");
    }

}
