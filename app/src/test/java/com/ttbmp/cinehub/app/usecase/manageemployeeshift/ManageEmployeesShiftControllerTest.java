package com.ttbmp.cinehub.app.usecase.manageemployeeshift;

import com.ttbmp.cinehub.app.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.app.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftController;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

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
        Assertions.assertDoesNotThrow(controller::getCinemaList);
    }

    @Test
    void getShiftList() {
        var start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        var end = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        Cinema cinema = null;
        try {
            cinema = cinemaRepository.getCinema(1);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assert cinema != null;
        var getShiftListRequest = new GetShiftListRequest(CinemaDataMapper.mapToDto(cinema), start, end);
        Assertions.assertDoesNotThrow(() -> controller.getShiftList(getShiftListRequest));

    }

    @Test
    void getEmployeeList() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        CinemaDto cinema = null;
        try {
            cinema = CinemaDataMapper.mapToDto(cinemaRepository.getCinema(1));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        var request = new GetEmployeeListRequest(cinema);
        Assertions.assertDoesNotThrow(() -> controller.getEmployeeList(request));
    }

    @Test
    void modifyShift() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        ShiftDto shift = null;
        try {
            shift = ShiftDataMapper.mapToDto(serviceLocator.getService(ShiftRepository.class).getShift(2));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assert shift != null;
        var request = new ShiftModifyRequest(
                shift.getEmployee(),
                shift.getId(),
                shift.getDate().plusDays(1),
                shift.getStart(),
                shift.getEnd(),
                null);
        Assertions.assertDoesNotThrow(() -> controller.modifyShift(request));
    }

    @Test
    void deleteShift() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        Shift finalShift = null;
        try {
            finalShift = serviceLocator.getService(ShiftRepository.class)
                    .getShift(1);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
        assert finalShift != null;
        var request = new ShiftRequest(finalShift.getId());
        Assertions.assertDoesNotThrow(() -> controller.deleteShift(request));

    }

    @Test
    void saveRepeatedShift() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        EmployeeDto employee = null;
        try {
            employee = EmployeeDataMapper.mapToDto(serviceLocator.getService(EmployeeRepository.class)
                    .getEmployee("0")
            );
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        var request = new ShiftRepeatRequest(
                LocalDate.now().plusYears(2),
                LocalDate.now().plusDays(7).plusYears(2),
                "EVERY_DAY",
                employee,
                LocalTime.now().withNano(0).withSecond(0),
                LocalTime.now().withNano(0).withSecond(0).plusHours(2),
                null
        );
        Assertions.assertDoesNotThrow(() -> controller.saveRepeatedShift(request));
    }

    @Test
    void createShift() {
        var controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeePresenter()
        );
        var request = new CreateShiftRequest(
                "0",
                LocalDate.now().plusMonths(2),
                LocalTime.now().withNano(0).withSecond(0),
                LocalTime.now().plusHours(1).withNano(0).withSecond(0)
        );
        Assertions.assertDoesNotThrow(() -> controller.createShift(request));
    }


    class MockManageEmployeePresenter implements ManageEmployeesShiftPresenter {

        @Override
        public void presentShiftList(GetShiftListResponse response) {
            var start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            var end = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            List<Shift> shiftList = null;
            try {
                shiftList = serviceLocator.getService(ShiftRepository.class)
                        .getCinemaShiftListBetween(response.getCinemaId(),
                                start,
                                end);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            var expected = ShiftDataMapper.mapToDtoList(shiftList);
            Assertions.assertEquals(expected, response.getShiftDtoList());
        }

        @Override
        public void presentEmployeeList(GetEmployeeListResponse response) {
            Cinema cinema = null;
            try {
                cinema = serviceLocator.getService(CinemaRepository.class).getCinema(1);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            List<Employee> employeeList = null;
            try {
                employeeList = serviceLocator.getService(EmployeeRepository.class).getEmployeeList(cinema);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            var expected = EmployeeDataMapper.mapToDtoList(employeeList);
            Assertions.assertEquals(expected, response.getEmployeeDtoList());
        }

        @Override
        public void presentCinemaList(GetCinemaListResponse listCinema) {
            List<Cinema> result = null;
            try {
                result = serviceLocator.getService(CinemaRepository.class).getAllCinema();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            var expected = CinemaDataMapper.mapToDtoList(result);
            Assertions.assertEquals(expected, listCinema.getCinemaList());
        }

        @Override
        public void presentSaveShift() {

        }

        @Override
        public void presentDeleteShift() {
            Employee employee = null;
            var shiftRepository = serviceLocator.getService(ShiftRepository.class);
            try {
                employee = serviceLocator.getService(EmployeeRepository.class).getEmployee("0");
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            Shift shift = null;
            try {
                shift = shiftRepository.getShift(1);

            } catch (RepositoryException e) {
                e.printStackTrace();
            }

            Assertions.assertNull(shift);
        }

        @Override
        public void presentRepeatShift(ShiftRepeatResponse response) {
            Employee employee = null;
            try {
                employee = serviceLocator.getService(EmployeeRepository.class).getEmployee("0");
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            List<ShiftDto> shiftList = null;
            try {
                shiftList = ShiftDataMapper.mapToDtoList(serviceLocator.getService(ShiftRepository.class).getShiftList(employee));
            } catch (RepositoryException e) {
                e.printStackTrace();
            }

            assert shiftList != null;
            Assertions.assertTrue(shiftList.containsAll(response.getShiftDto()));
        }

        @Override
        public void presentCreateShift(CreateShiftResponse response) {
            Employee employee = null;
            try {
                employee = serviceLocator.getService(EmployeeRepository.class).getEmployee("0");
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            Shift shift = null;

            try {
                shift = serviceLocator.getService(ShiftRepository.class)
                        .getShift(
                                employee,
                                LocalDate.now().plusMonths(2).toString(),
                                LocalTime.now().withNano(0).withSecond(0).toString(),
                                LocalTime.now().withNano(0).withSecond(0).plusHours(1).toString()
                        );
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            var expected = ShiftDataMapper.mapToDto(shift);
            Assertions.assertEquals(expected, response.getShiftDto());
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

    }
}