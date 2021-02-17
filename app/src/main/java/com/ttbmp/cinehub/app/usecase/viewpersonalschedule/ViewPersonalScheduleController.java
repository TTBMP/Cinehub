package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.user.Permission;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleController implements ViewPersonalScheduleUseCase {

    private final ViewPersonalSchedulePresenter presenter;

    private final EmployeeRepository employeeRepository;
    private final ProjectionistShiftRepository projectionistShiftRepository;

    private final ServiceLocator serviceLocator;

    public ViewPersonalScheduleController(ServiceLocator serviceLocator, ViewPersonalSchedulePresenter presenter) {
        this.presenter = presenter;
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        this.projectionistShiftRepository = serviceLocator.getService(ProjectionistShiftRepository.class);
        this.serviceLocator = serviceLocator;
    }

    public void getShiftList(ShiftListRequest request) {
        try {
            AuthenticatedRequest.validate(
                    request,
                    new Permission[]{Permission.GET_OWN_SHIFT_LIST},
                    serviceLocator
            );
            var employee = employeeRepository.getEmployee(request.getUser().getId());
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
        }
    }

    @Override
    public void getShiftProjectionList(ProjectionListRequest request) {
        try {
            Request.validate(request);
            var shift = projectionistShiftRepository.getProjectionistShift(request.getProjectionistShiftId());
            presenter.presentGetProjectionList(new ProjectionListReply(shift.getProjectionList()));
        } catch (Request.NullRequestException e) {
            presenter.presentProjectionListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidProjectionListRequest(request);
        }
    }

}
