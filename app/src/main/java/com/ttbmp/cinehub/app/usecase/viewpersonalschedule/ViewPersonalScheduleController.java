package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.repository.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.Projectionist;
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

    public ViewPersonalScheduleController(
            ViewPersonalSchedulePresenter presenter,
            ShiftRepository shiftRepository,
            EmployeeRepository employeeRepository,
            ProjectionRepository projectionRepository,
            AuthenticationService authenticationService) {
        this.presenter = presenter;
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
        this.projectionRepository = projectionRepository;
        this.authenticationService = authenticationService;
    }

    public void getShiftList(GetShiftListRequest request) {
        try {
            Request.validate(request);
            int userId = authenticationService.sigIn();
            Employee employee = employeeRepository.getEmployee(userId);
            List<Shift> shiftList = shiftRepository.getAllEmployeeShiftBetweenDate(
                    employee,
                    request.getStart(),
                    request.getEnd()
            );
            presenter.presentGetShiftList(new GetShiftListReply(shiftList));
        } catch (Request.NullRequestException e) {
            presenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidGetShiftListRequest(request);
        }
    }

    @Override
    public void getShiftProjectionList(GetShiftProjectionListRequest request) {
        try {
            Request.validate(request);
            ProjectionistShift shift = (ProjectionistShift) ShiftDataMapper.mapToEntity(request.getShiftDto());
            List<Projection> projectionList = projectionRepository.getProjectionList(shift);
            presenter.presentGetProjectionList(new GetShiftProjectionListReply(projectionList));
        } catch (Request.NullRequestException e) {
            e.printStackTrace();
        } catch (Request.InvalidRequestException e) {
            e.printStackTrace();
        }
    }

}
