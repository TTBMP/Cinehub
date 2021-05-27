package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

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

    }

    @Override
    public void presentInvalidRequest(Request request) {

    }

    @Override
    public void presentRepositoryError(RepositoryException e) {

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {

    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {

    }

}
