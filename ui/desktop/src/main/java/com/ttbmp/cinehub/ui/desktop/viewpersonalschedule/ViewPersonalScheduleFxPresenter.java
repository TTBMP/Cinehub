package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ShiftListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalSchedulePresenter;
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
        viewModel.setCurrentEmployee(result.getEmployeeDto());
        viewModel.getShiftList().setAll(result.getShiftDtoList());
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply projectionListReply) {
        viewModel.getProjectionList().setAll(projectionListReply.getProjectionDtoList());
    }

    @Override
    public void presentNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.setErrorMessage(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

}
