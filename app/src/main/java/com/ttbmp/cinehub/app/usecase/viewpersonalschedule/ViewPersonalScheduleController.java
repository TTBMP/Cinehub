package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationException;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleController implements ViewPersonalScheduleUseCase {

    private final ViewPersonalSchedulePresenter presenter;

    private final EmployeeRepository employeeRepository;
    private final ProjectionistShiftRepository projectionistShiftRepository;
    private final AuthenticationService authenticationService;

    public ViewPersonalScheduleController(ServiceLocator serviceLocator, ViewPersonalSchedulePresenter presenter) {
        this.presenter = presenter;
        this.authenticationService = serviceLocator.getService(AuthenticationService.class);
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        this.projectionistShiftRepository = serviceLocator.getService(ProjectionistShiftRepository.class);
    }

    public void getShiftList(ShiftListRequest request) {
        try {
            Request.validate(request);
            var userId = authenticationService.signIn("", "").getUserId();
            var employee = employeeRepository.getEmployee(userId);
            presenter.presentGetShiftList(new ShiftListReply(
                    employee.getShiftListBetween(request.getStart(), request.getEnd())
            ));
        } catch (Request.NullRequestException e) {
            presenter.presentShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidShiftListRequest(request);
        } catch (AuthenticationException e) {
            presenter.presentAuthenticationError(e);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
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
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

}
