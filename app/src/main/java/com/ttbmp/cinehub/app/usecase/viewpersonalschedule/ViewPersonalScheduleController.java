package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.app.repository.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.app.utilities.result.Result;

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
            Employee employeeResult = employeeRepository.getEmployee(userId);
            /*if (employeeResult.hasError()) {
                presenter.presentAuthenticationError(employeeResult.getError());
                return;
            }*/
            Result<List<Shift>> shiftListResult = shiftRepository.getAllEmployeeShiftBetweenDate(
                    employeeResult,
                    request.getStart(),
                    request.getEnd()
            );
            if (shiftListResult.hasError()) {
                presenter.presentShiftListError(shiftListResult.getError());
                return;
            }
            presenter.presentGetShiftList(new Result<>(new GetShiftListResponse(shiftListResult.getValue())));
        } catch (Request.NullRequestException e) {
            presenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidGetShiftListRequest(request);
        }
    }

}
