package com.ttbmp.cinehub.core.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.repository.EmployeeRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.utilities.result.Result;

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
        if (request == null) {
            presenter.presentGetShiftListNullRequest();
            return;
        }
        request.validate();
        if (request.getNotification().hasError()) {
            presenter.presentGetShiftListRequestInvalid(request);
            return;
        }
        int userId = authenticationService.sigIn();
        Result<Employee> employeeResult = employeeRepository.getEmployee(userId);
        if (employeeResult.hasError()) {
            presenter.presentAuthenticationError(employeeResult.getError());
            return;
        }
        Result<List<Shift>> shiftListResult = shiftRepository.getAllEmployeeShiftBetweenDate(
                employeeResult.getValue(),
                request.getStart(),
                request.getEnd()
        );
        if (shiftListResult.hasError()) {
            presenter.presentShiftListError(shiftListResult.getError());
            return;
        }
        presenter.presentGetShiftList(new Result<>(new GetShiftListResponse(shiftListResult.getValue())));
    }

}
