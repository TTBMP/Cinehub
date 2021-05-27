package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockViewPersonalSchedulePresenter implements ViewPersonalSchedulePresenter {

    private final MockViewPersonalScheduleViewModel viewModel;

    public MockViewPersonalSchedulePresenter(MockViewPersonalScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentGetShiftList(ShiftListReply result) {
        viewModel.setShiftList(result.getShiftDtoList());
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply result) {
        viewModel.setProjectionList(result.getProjectionDtoList());
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
