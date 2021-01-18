package com.ttbmp.cinehub.core.usecase;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.presenter.ViewPersonalSchedulePresenter;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleUseCase implements UseCase {

    private final ViewPersonalSchedulePresenter presenter;
    private final ShiftRepository shiftRepository;
    private final AuthenticationService authenticationService;

    public ViewPersonalScheduleUseCase(
            ViewPersonalSchedulePresenter presenter,
            ShiftRepository shiftRepository,
            AuthenticationService authenticationService) {
        Objects.requireNonNull(shiftRepository);
        Objects.requireNonNull(presenter);
        Objects.requireNonNull(authenticationService);
        this.shiftRepository = shiftRepository;
        this.authenticationService = authenticationService;
        this.presenter = presenter;
    }

    public void getShiftList(LocalDate start, LocalDate end) {
        Employee employee = (Employee) authenticationService.getCurrentSession().getUser();
        presenter.presentShiftList(shiftRepository.getAllEmployeeShiftBetweenDate(employee, start, end));
    }

}
