package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

public class MockManageEmployeeShiftPresenter implements ManageEmployeesShiftPresenter {

    private final MockManageEmployeeShiftViewModel viewModel;

    public MockManageEmployeeShiftPresenter(MockManageEmployeeShiftViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentShiftList(GetShiftListReply reply) {
        viewModel.setShiftList(reply.getShiftDtoList());
    }

    @Override
    public void presentEmployeeList(GetEmployeeListReply reply) {
        viewModel.setEmployeeList(reply.getEmployeeDtoList());
    }

    @Override
    public void presentCinemaList(GetCinemaListReply reply) {
        viewModel.setCinemaList(reply.getCinemaList());
    }

    @Override
    public void presentSaveShift() {

    }

    @Override
    public void presentDeleteShift() {
        viewModel.setShift(null);
    }

    @Override
    public void presentRepeatShift(ShiftRepeatReply reply) {
        viewModel.setShiftList(reply.getShiftDto());
    }

    @Override
    public void presentCreateShift(CreateShiftReply reply) {
        viewModel.setShift(reply.getShiftDto());
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
    public void presentSendEmailServiceException(EmailServiceException error) {
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
    public void presentApplicationError(CinehubException e) {
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
