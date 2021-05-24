package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public class MockManageEmployeeShiftPresenter implements ManageEmployeesShiftPresenter {

    private final MockManageEmployeeShiftViewModel viewModel;

    public MockManageEmployeeShiftPresenter(MockManageEmployeeShiftViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentShiftList(GetShiftListResponse shiftList) {
        viewModel.setShiftList(shiftList.getShiftDtoList());
    }

    @Override
    public void presentEmployeeList(GetEmployeeListResponse employeeList) {
        viewModel.setEmployeeList(employeeList.getEmployeeDtoList());
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse listCinema) {
        viewModel.setCinemaList(listCinema.getCinemaList());
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
    public void presentInvalidGetCinemaListRequest(GetCinemaListRequest request) {

    }

    @Override
    public void presentCinemaListNullRequest() {

    }

    @Override
    public void presentCreateShiftError(Throwable error) {

    }

    @Override
    public void presentInvalidEmployeeListRequest(GetEmployeeListRequest request) {

    }

    @Override
    public void presentEmployeeListNullRequest() {

    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {

    }

    @Override
    public void presentDeleteShiftNullRequest() {

    }

    @Override
    public void presentInvalidModifyShiftListRequest(ShiftModifyRequest request) {

    }

    @Override
    public void presentModifyShiftNullRequest() {

    }

    @Override
    public void presentModifyShiftError(Throwable error) {

    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {

    }

    @Override
    public void presentCreateShiftNullRequest() {

    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {

    }

    @Override
    public void presentRepeatedShiftNullRequest() {

    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {

    }

    @Override
    public void presentGetShiftListNullRequest() {

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
