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
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private MockManageEmployeeShiftViewModel viewModel;
    private ManageEmployeesShiftController controller;

    @BeforeEach
    void setPresenter() {
        viewModel = new MockManageEmployeeShiftViewModel();
        controller = new ManageEmployeesShiftController(
                serviceLocator,
                new MockManageEmployeeShiftPresenter(viewModel)
        );
    }

    private void logInAsManager() {
        viewModel.setSessionToken("5KClU7hbNgedJAwLuF9eFVl6Qzz2");
    }

    @Test
    void getCinemaList() throws RepositoryException {
        logInAsManager();
        var request = new GetCinemaListRequest(viewModel.getSessionToken());
        controller.getCinemaList(request);
        var expected = getCinemaListExpected();
        var actual = viewModel.getCinemaList();
        Assertions.assertEquals(expected, actual);
    }

    private List<CinemaDto> getCinemaListExpected() throws RepositoryException {
        List<Cinema> result = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        return CinemaDataMapper.mapToDtoList(result);

    }

    @Test
    void getShiftList() throws RepositoryException {
        logInAsManager();
        var start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        var end = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        Cinema cinema = null;
        try {
            cinema = cinemaRepository.getCinema(1);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assert cinema != null;
        var getShiftListRequest = new GetShiftListRequest(viewModel.getSessionToken(), CinemaDataMapper.mapToDto(cinema), start, end);
        controller.getShiftList(getShiftListRequest);
        var expected = getShiftListExpected(getShiftListRequest);
        var actual = viewModel.getShiftList();
        Assertions.assertEquals(expected, actual);

    }

    private List<ShiftDto> getShiftListExpected(GetShiftListRequest request) throws RepositoryException {
        List<Shift> shiftList = serviceLocator.getService(ShiftRepository.class)
                .getCinemaShiftListBetween(
                        request.getCinema().getId(),
                        request.getStart(),
                        request.getEnd()
                );
        return ShiftDataMapper.mapToDtoList(shiftList);

    }

    @Test
    void getEmployeeList() throws RepositoryException {
        logInAsManager();
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        CinemaDto cinema = CinemaDataMapper.mapToDto(cinemaRepository.getCinema(1));
        var request = new GetEmployeeListRequest(viewModel.getSessionToken(), cinema);
        controller.getEmployeeList(request);
        var expected = getEmployeeListExpected(request);
        var actual = viewModel.getEmployeeList();
        Assertions.assertEquals(expected, actual);


    }

    private List<EmployeeDto> getEmployeeListExpected(GetEmployeeListRequest request) throws RepositoryException {
        Cinema cinema = serviceLocator.getService(CinemaRepository.class).getCinema(request.getCinema().getId());
        List<Employee> employeeList = serviceLocator.getService(EmployeeRepository.class).getEmployeeList(cinema);
        return EmployeeDataMapper.mapToDtoList(employeeList);
    }


    @Test
    void modifyShift() throws RepositoryException {
        logInAsManager();
        var shift = ShiftDataMapper.mapToDto(serviceLocator.getService(ShiftRepository.class).getShift(2));
        var employee= EmployeeDataMapper.mapToDto(serviceLocator.getService(EmployeeRepository.class).getEmployee(shift.getEmployeeId()));

        var request = new ShiftModifyRequest(
                viewModel.getSessionToken(),
                employee,
                shift.getId(),
                shift.getDate().plusDays(1),
                shift.getStart(),
                shift.getEnd(),
                null);
        Assertions.assertDoesNotThrow(() -> controller.modifyShift(request));
    }

    @Test
    void deleteShift() throws RepositoryException {
        logInAsManager();
        var request = new ShiftRequest(viewModel.getSessionToken(), 1);
        controller.deleteShift(request);
        Assertions.assertNull(deleteShiftExpected(request));

    }

    private Shift deleteShiftExpected(ShiftRequest request) throws RepositoryException {
        var shiftRepository = serviceLocator.getService(ShiftRepository.class);
        return shiftRepository.getShift(request.getShiftId());
    }

    @Test
    void saveRepeatedShift() throws RepositoryException {
        logInAsManager();
        EmployeeDto employee = EmployeeDataMapper.mapToDto(serviceLocator.getService(EmployeeRepository.class)
                .getEmployee("gVUYDMojhmeFkErlbF0WWLQWMPn1")
        );
        var request = new ShiftRepeatRequest(
                viewModel.getSessionToken(),
                LocalDate.now().plusYears(2),
                LocalDate.now().plusDays(7).plusYears(2),
                "EVERY_DAY",
                employee,
                LocalTime.now().withNano(0).withSecond(0).minusHours(2),
                LocalTime.now().withNano(0).withSecond(0),
                null
        );
        controller.createRepeatedShift(request);
        var expected = getSaveRepeatedShiftExpected(request);
        Assertions.assertTrue(expected.containsAll(viewModel.getShiftList()));
    }

    private List<ShiftDto> getSaveRepeatedShiftExpected(ShiftRepeatRequest request) throws RepositoryException {
        Employee employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(request.getEmployeeDto().getId());
        return ShiftDataMapper.mapToDtoList(serviceLocator.getService(ShiftRepository.class).getShiftList(employee));
    }

    @Test
    void createShift() throws RepositoryException {
        logInAsManager();
        var request = new CreateShiftRequest(
                viewModel.getSessionToken(),
                "gVUYDMojhmeFkErlbF0WWLQWMPn1",
                LocalDate.now().plusMonths(2),
                LocalTime.now().withNano(0).withSecond(0),
                LocalTime.now().plusHours(1).withNano(0).withSecond(0)
        );
        controller.createShift(request);
        var expected = createShiftExpected(request);
        var actual = viewModel.getShift();
        Assertions.assertEquals(expected, actual);
    }

    private ShiftDto createShiftExpected(CreateShiftRequest request) throws RepositoryException {
        Employee employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(request.getEmployeeId());
        return ShiftDataMapper.mapToDto(serviceLocator.getService(ShiftRepository.class)
                .getShift(
                        employee,
                        request.getDate().toString(),
                        request.getStart().toString(),
                        request.getEnd().toString()
                )
        );

    }


}
