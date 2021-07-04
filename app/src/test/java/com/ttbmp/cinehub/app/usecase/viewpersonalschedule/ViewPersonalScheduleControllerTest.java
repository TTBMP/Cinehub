package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ShiftListRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
class ViewPersonalScheduleControllerTest {

    private MockServiceLocator serviceLocator;
    private MockViewPersonalScheduleViewModel viewModel;
    private ViewPersonalScheduleController controller;

    @BeforeEach
    void setPresenter() {
        serviceLocator = new MockServiceLocator();
        viewModel = new MockViewPersonalScheduleViewModel();
        controller = new ViewPersonalScheduleController(
                serviceLocator,
                new MockViewPersonalSchedulePresenter(viewModel)
        );
    }

    @Test
    void getShiftList() throws RepositoryException, SecurityException {
        logInAsProjectionist();
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        controller.getShiftList(request);
        var expected = getExpectedShiftList(request);
        var actual = viewModel.getShiftList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getShiftListUnauthenticated_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftListWithNullDates_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), null, null);
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftListWithNullStart_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), null, LocalDate.now());
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftListWithNullEnd_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), null);
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftListWithStartAfterEnd_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now().plusDays(7), LocalDate.now());
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftProjectionListUnauthenticated_SetErrorMessageInViewModel() {
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        controller.getShiftList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    @Test
    void getShiftProjectionList() throws RepositoryException, SecurityException {
        logInAsProjectionist();
        var shiftListRequest = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        var shiftId = getExpectedShiftList(shiftListRequest).get(0).getId();
        var request = new ProjectionListRequest(viewModel.getSessionToken(), shiftId);
        controller.getShiftProjectionList(request);
        var expected = getExpectedShiftProjectionList(request);
        var actual = viewModel.getProjectionList();
        Assertions.assertArrayEquals(
                expected.stream().map(ProjectionDto::getId).toArray(),
                actual.stream().map(ProjectionDto::getId).toArray()
        );
    }

    private void logInAsProjectionist() {
        viewModel.setSessionToken("T8SP2uHYdHZfBk6uh3fJ356Sji52");
    }

    private List<ShiftDto> getExpectedShiftList(ShiftListRequest request) throws RepositoryException, SecurityException {
        var userId = serviceLocator.getService(SecurityService.class).authenticate(request.getSessionToken()).getId();
        var employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(userId);
        var shiftList = serviceLocator.getService(ShiftRepository.class).getAllEmployeeShiftBetweenDate(
                employee,
                request.getStart(),
                request.getEnd()
        );
        return shiftList.stream()
                .map(ShiftDto::new)
                .collect(Collectors.toList());
    }

    private List<ProjectionDto> getExpectedShiftProjectionList(ProjectionListRequest request) throws RepositoryException {
        var projectionistShift = serviceLocator.getService(ProjectionistShiftRepository.class)
                .getProjectionistShift(request.getProjectionistShiftId());
        var projectionList = serviceLocator.getService(ProjectionRepository.class)
                .getProjectionList(projectionistShift);
        return projectionList.stream()
                .map(ProjectionDto::new)
                .collect(Collectors.toList());
    }

}
