package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
class ViewPersonalScheduleControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getShiftList() {
        var controller = new ViewPersonalScheduleController(
                serviceLocator,
                new MockViewPersonalSchedulePresenter()
        );
        var request = new ShiftListRequest(
                "",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );
        Assertions.assertDoesNotThrow(() -> controller.getShiftList(request));
    }

    @Test
    void getShiftProjectionList() {

    }

    class MockViewPersonalSchedulePresenter implements ViewPersonalSchedulePresenter {

        @Override
        public void presentGetShiftList(ShiftListReply result) {
            String userId = null;
            try {
                userId = serviceLocator.getService(SecurityService.class).authenticate("").getId();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            Employee employee = null;
            try {
                employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(userId);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            List<Shift> shiftList = null;
            try {
                shiftList = serviceLocator.getService(ShiftRepository.class).getAllEmployeeShiftBetweenDate(
                        employee,
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                );
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
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

        @Override
        public void presentSecurityError(SecurityException e) {

        }

        @Override
        public void presentRepositoryError(RepositoryException e) {

        }

    }

}
