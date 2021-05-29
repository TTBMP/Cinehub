package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

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
        viewModel.setErrorMessage(error.getMessage());
    }


    @Override
    public void presentModifyShiftError(Throwable error) {
        viewModel.setErrorMessage(error.getMessage());
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
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());
    }
}
