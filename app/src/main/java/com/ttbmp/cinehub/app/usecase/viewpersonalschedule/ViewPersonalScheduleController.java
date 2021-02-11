package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleController implements ViewPersonalScheduleUseCase {

    private final ViewPersonalSchedulePresenter presenter;

    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectionRepository projectionRepository;
    private final AuthenticationService authenticationService;

    public ViewPersonalScheduleController(ServiceLocator serviceLocator, ViewPersonalSchedulePresenter presenter) {
        this.presenter = presenter;
        this.shiftRepository = serviceLocator.getService(ShiftRepository.class);
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        this.authenticationService = serviceLocator.getService(AuthenticationService.class);
    }

    public void getShiftList(ShiftListRequest request) {
        try {
            Request.validate(request);
            int userId = authenticationService.sigInOld();
            Employee employee = employeeRepository.getEmployee(userId);
            List<Shift> shiftList = shiftRepository.getAllEmployeeShiftBetweenDate(
                    employee,
                    request.getStart(),
                    request.getEnd()
            );
            presenter.presentGetShiftList(new ShiftListReply(shiftList));
        } catch (Request.NullRequestException e) {
            presenter.presentShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidShiftListRequest(request);
        }
    }

    @Override
    public void getShiftProjectionList(ProjectionListRequest request) {
        try {
            Request.validate(request);
            ProjectionistShift shift = (ProjectionistShift) ShiftDataMapper.mapToEntity(request.getShiftDto());
            List<Projection> projectionList = projectionRepository.getProjectionList(shift);
            presenter.presentGetProjectionList(new ProjectionListReply(projectionList));
        } catch (Request.NullRequestException e) {
            presenter.presentProjectionListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidProjectionListRequest(request);
        }
    }

}
