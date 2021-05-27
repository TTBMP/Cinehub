package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

public class MockManageEmployeeShiftPresenter implements ManageEmployeesShiftPresenter {

    private final MockManageEmployeeShiftViewModel viewModel;

    public MockManageEmployeeShiftPresenter(MockManageEmployeeShiftViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentShiftList(GetShiftListResponse response) {
        viewModel.setShiftList(response.getShiftDtoList());
    }

    @Override
    public void presentEmployeeList(GetEmployeeListResponse response) {
        viewModel.setEmployeeList(response.getEmployeeDtoList());
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse response) {
        viewModel.setCinemaList(response.getCinemaList());
    }

    @Override
    public void presentSaveShift() {

    }

    @Override
    public void presentDeleteShift() {
        viewModel.setShift(null);
    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        viewModel.setShiftList(response.getShiftDto());
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        viewModel.setShift(response.getShiftDto());
    }


    @Override
    public void presentCreateShiftError(Throwable error) {

    }


    @Override
    public void presentModifyShiftError(Throwable error) {

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
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {

    }
}
