package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.*;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleFxPresenter implements ViewPersonalSchedulePresenter {

    private final ViewPersonalScheduleViewModel viewModel;

    public ViewPersonalScheduleFxPresenter(ViewPersonalScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentGetShiftList(ShiftListReply result) {
        viewModel.getShiftList().setAll(result.getShiftDtoList());
    }

    @Override
    public void presentInvalidShiftListRequest(ShiftListRequest request) {
        if (request.getErrorList().contains(ShiftListRequest.MISSING_START_TIME_ERROR)) {
            System.out.println(ShiftListRequest.MISSING_START_TIME_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ShiftListRequest.MISSING_END_TIME_ERROR)) {
            System.out.println(ShiftListRequest.MISSING_END_TIME_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ShiftListRequest.INVALID_TIME_SELECTION_ERROR)) {
            System.out.println(ShiftListRequest.INVALID_TIME_SELECTION_ERROR.getMessage());
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
    public void presentShiftListNullRequest() {
        System.out.println("Request can't be null");
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply projectionListReply) {
        viewModel.getProjectionList().setAll(projectionListReply.getProjectionDtoList());
    }

    @Override
    public void presentProjectionListNullRequest() {
        System.out.println("Request can't be null");
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        if (request.getErrorList().contains(ProjectionListRequest.INVALID_SHIFT_ERROR)) {
            System.out.println(ProjectionListRequest.INVALID_SHIFT_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_SHIFT_ERROR)) {
            System.out.println(ProjectionListRequest.MISSING_SHIFT_ERROR.getMessage());
        }
    }

}
