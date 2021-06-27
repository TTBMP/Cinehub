package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalSchedulePresenter;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ShiftListReply;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

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
        viewModel.currentEmployeeProperty().set(result.getEmployeeDto());
        viewModel.getShiftList().setAll(result.getShiftDtoList());
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply projectionListReply) {
        viewModel.getProjectionList().setAll(projectionListReply.getProjectionDtoList());
    }

    @Override
    public void presentNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.errorMessageProperty().setValue(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }

    @Override
    public void presentApplicationError(CinehubException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

}
