package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.security.Permission;

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
            presenter.presentGetShiftList(new ShiftListReply(
                    employee.getShiftListBetween(request.getStart(), request.getEnd())
            ));
        } catch (Request.NullRequestException e) {
            presenter.presentShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidShiftListRequest(request);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            e.printStackTrace();
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            e.printStackTrace();
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
            presenter.presentGetProjectionList(new ProjectionListReply(shift.getProjectionList()));
        } catch (Request.NullRequestException e) {
            presenter.presentProjectionListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidProjectionListRequest(request);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            e.printStackTrace();
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

}
