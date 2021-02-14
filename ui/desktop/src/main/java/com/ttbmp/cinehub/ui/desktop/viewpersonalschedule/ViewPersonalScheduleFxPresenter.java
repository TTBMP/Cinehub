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
        String message = "";
        if (request.getErrorList().contains(ShiftListRequest.MISSING_START_TIME_ERROR)) {
            message += ShiftListRequest.MISSING_START_TIME_ERROR.getMessage() + "\n";
        }
        if (request.getErrorList().contains(ShiftListRequest.MISSING_END_TIME_ERROR)) {
            message += ShiftListRequest.MISSING_END_TIME_ERROR.getMessage() + "\n";
        }
        if (request.getErrorList().contains(ShiftListRequest.INVALID_TIME_SELECTION_ERROR)) {
            message += ShiftListRequest.INVALID_TIME_SELECTION_ERROR.getMessage() + "\n";
        }
        viewModel.setErrorMessage(message);
    }

    @Override
    public void presentShiftListNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply projectionListReply) {
        viewModel.getProjectionList().setAll(projectionListReply.getProjectionDtoList());
    }

    @Override
    public void presentProjectionListNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        String message = "";
        if (request.getErrorList().contains(ProjectionListRequest.INVALID_SHIFT_ID_ERROR)) {
            message += ProjectionListRequest.INVALID_SHIFT_ID_ERROR.getMessage() + "\n";
        }
        viewModel.setErrorMessage(message);
    }

}
