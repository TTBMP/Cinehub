package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationException;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ViewPersonalScheduleControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getShiftList() {
        var controller = new ViewPersonalScheduleController(
                serviceLocator,
                new MockViewPersonalSchedulePresenter()
        );
        var request = new ShiftListRequest(
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );
        controller.getShiftList(request);
    }

    @Test
    void getShiftProjectionList() {

    }

    class MockViewPersonalSchedulePresenter implements ViewPersonalSchedulePresenter {

        @Override
        public void presentGetShiftList(ShiftListReply result) {
            String userId = null;
            try {
                userId = serviceLocator.getService(AuthenticationService.class).signIn("", "").getUserId();
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
            var employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(userId);
            var shiftList = serviceLocator.getService(ShiftRepository.class).getAllEmployeeShiftBetweenDate(
                    employee,
                    LocalDate.now(),
                    LocalDate.now().plusDays(1)
            );
            var expected = ShiftDataMapper.mapToDtoList(shiftList);
            var actual = result.getShiftDtoList();
            Assertions.assertEquals(expected, result.getShiftDtoList());
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

    }

}