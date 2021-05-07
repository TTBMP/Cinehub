package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftController;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.domain.Cinema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 **/
class ManageEmployeesShiftControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getCinemaList() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        controller.getCinemaList();
    }

    @Test
    void getShiftList() throws RepositoryException {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        var cinema = cinemaRepository.getCinema(0);
        var getShiftListRequest = new GetShiftListRequest(CinemaDataMapper.mapToDto(cinema), LocalDate.now(), LocalDate.now());
        controller.getShiftList(getShiftListRequest);
    }


    static class MockManageEmployeePresenter implements ManageEmployeesShiftPresenter {

        @Override
        public void presentShiftList(GetShiftListResponse shiftList) {
            Assertions.assertNotEquals(0, shiftList.getShiftDtoList().size());
        }

        @Override
        public void presentEmployeeList(GetEmployeeListResponse employeeList) {

        }

        @Override
        public void presentCinemaList(GetCinemaListResponse listCinema) {
            Assertions.assertNotEquals(0, listCinema.getCinemaList().size());
        }


        @Override
        public void presentSaveShift() {

        }

        @Override
        public void presentDeleteShift() {

        }

        @Override
        public void presentRepeatShift(ShiftRepeatResponse response) {

        }

        @Override
        public void presentCreateShift(CreateShiftResponse response) {

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
        public void presentDeleteShiftError(Throwable error) {

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

    }
}