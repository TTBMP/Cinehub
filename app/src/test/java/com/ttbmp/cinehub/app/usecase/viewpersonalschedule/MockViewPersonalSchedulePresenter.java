package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

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
    public void presentInvalidShiftListRequest(ShiftListRequest request) {

    }

    @Override
    public void presentShiftListNullRequest() {

    }

    @Override
    public void presentGetProjectionList(ProjectionListReply projectionListReply) {

    }

    @Override
    public void presentProjectionListNullRequest() {

    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {

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
