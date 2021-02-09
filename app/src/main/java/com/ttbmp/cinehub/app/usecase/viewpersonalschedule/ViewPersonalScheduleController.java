package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.repository.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleController implements ViewPersonalScheduleUseCase {

    private final ViewPersonalSchedulePresenter presenter;
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationService authenticationService;

    public ViewPersonalScheduleController(
            ViewPersonalSchedulePresenter presenter,
            ShiftRepository shiftRepository,
            EmployeeRepository employeeRepository,
            AuthenticationService authenticationService) {
        this.presenter = presenter;
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
        this.authenticationService = authenticationService;
    }

    public void getShiftList(GetShiftListRequest request) {
        try {
            Request.validate(request);
            int userId = authenticationService.sigIn();
            Employee employee = employeeRepository.getEmployee(userId);
            List<Shift> shiftListResult = shiftRepository.getAllEmployeeShiftBetweenDate(
                    employee,
                    request.getStart(),
                    request.getEnd()
            );
            presenter.presentGetShiftList(new GetShiftListResponse(shiftListResult));
        } catch (Request.NullRequestException e) {
            presenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidGetShiftListRequest(request);
        }
    }

}
