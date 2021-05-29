package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDtoFactory;
import com.ttbmp.cinehub.app.dto.shift.ShiftDtoFactory;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.security.Permission;

import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleController implements ViewPersonalScheduleUseCase {

    private final ViewPersonalSchedulePresenter presenter;

    private final EmployeeRepository employeeRepository;
    private final ProjectionistShiftRepository projectionistShiftRepository;
    private final SecurityService securityService;

    public ViewPersonalScheduleController(ServiceLocator serviceLocator, ViewPersonalSchedulePresenter presenter) {
        this.presenter = presenter;
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        this.projectionistShiftRepository = serviceLocator.getService(ProjectionistShiftRepository.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    public void getShiftList(ShiftListRequest request) {
        var permissions = new Permission[]{Permission.GET_OWN_SHIFT_LIST};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var employee = employeeRepository.getEmployee(request.getUserId());
            var shiftList = employee.getShiftListBetween(request.getStart(), request.getEnd());
            presenter.presentGetShiftList(new ShiftListReply(
                    EmployeeDtoFactory.getEmployeeDto(employee),
                    shiftList.stream()
                            .map(ShiftDtoFactory::getShiftDto)
                            .collect(Collectors.toList())
            ));
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

    @Override
    public void getShiftProjectionList(ProjectionListRequest request) {
        var permissions = new Permission[]{Permission.GET_OWN_SHIFT_PROJECTION_LIST};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var shift = projectionistShiftRepository.getProjectionistShift(request.getProjectionistShiftId());
            presenter.presentGetProjectionList(new ProjectionListReply(
                    shift.getProjectionList().stream()
                            .map(ProjectionDto::new)
                            .collect(Collectors.toList())
            ));
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

}
