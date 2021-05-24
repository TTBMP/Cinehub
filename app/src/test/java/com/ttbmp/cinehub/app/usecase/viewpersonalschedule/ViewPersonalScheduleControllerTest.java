package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ProjectionDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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

    private void logInAsProjectionist() {
        viewModel.setSessionToken("T8SP2uHYdHZfBk6uh3fJ356Sji52");
    }

    @Test
    void getShiftList() throws RepositoryException, SecurityException {
        logInAsProjectionist();
        var request = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        controller.getShiftList(request);
        var expected = getShiftList(request);
        var actual = viewModel.getShiftList();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void getShiftProjectionList() throws RepositoryException, SecurityException {
        logInAsProjectionist();
        var shiftListRequest = new ShiftListRequest(viewModel.getSessionToken(), LocalDate.now(), LocalDate.now().plusDays(7));
        var shiftId = getShiftList(shiftListRequest).get(0).getId();
        var request = new ProjectionListRequest(viewModel.getSessionToken(), shiftId);
        controller.getShiftProjectionList(request);
        var expected = getShiftProjectionList(request);
        var actual = viewModel.getProjectionList();
        Assertions.assertArrayEquals(
                expected.stream().map(ProjectionDto::getId).toArray(),
                actual.stream().map(ProjectionDto::getId).toArray()
        );
    }

    private List<ShiftDto> getShiftList(ShiftListRequest request) throws RepositoryException, SecurityException {
        var userId = serviceLocator.getService(SecurityService.class).authenticate(request.getSessionToken()).getId();
        var employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(userId);
        var shiftList = serviceLocator.getService(ShiftRepository.class).getAllEmployeeShiftBetweenDate(
                employee,
                request.getStart(),
                request.getEnd()
        );
        return ShiftDataMapper.mapToDtoList(shiftList);
    }

    private List<ProjectionDto> getShiftProjectionList(ProjectionListRequest request) throws RepositoryException {
        var projectionistShift = serviceLocator.getService(ProjectionistShiftRepository.class)
                .getProjectionistShift(request.getProjectionistShiftId());
        var projectionList = serviceLocator.getService(ProjectionRepository.class)
                .getProjectionList(projectionistShift);
        return ProjectionDataMapper.mapToDtoList(projectionList);
    }

}
