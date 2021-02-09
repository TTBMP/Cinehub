package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.GetShiftListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.GetShiftListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.GetShiftProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalSchedulePresenter;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleFxPresenter implements ViewPersonalSchedulePresenter {

    private final ViewPersonalScheduleViewModel viewModel;

    public ViewPersonalScheduleFxPresenter(ViewPersonalScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentGetShiftList(GetShiftListReply result) {
        viewModel.getShiftList().setAll(result.getShiftDtoList());
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

    @Override
    public void presentGetProjectionList(GetShiftProjectionListReply getShiftProjectionListReply) {
        viewModel.getProjectionList().setAll(getShiftProjectionListReply.getProjectionDtoList());
    }

}
