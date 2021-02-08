package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.core.repository.EmployeeRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.usecase.viewpersonalschedule.ViewPersonalScheduleController;
import com.ttbmp.cinehub.core.usecase.viewpersonalschedule.ViewPersonalScheduleUseCase;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.PersonalScheduleView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleActivity extends Activity {

    private final ShiftRepository shiftRepository = CinehubApplication.APP_CONTAINER.getFactory(ShiftRepository.class).get();
    private final EmployeeRepository employeeRepository = CinehubApplication.APP_CONTAINER.getFactory(EmployeeRepository.class).get();
    private final AuthenticationService authenticationService = CinehubApplication.APP_CONTAINER.getFactory(AuthenticationService.class).get();

    public ViewPersonalScheduleActivity() throws IOException {
        super(new PersonalScheduleView());
        viewModelStore.put(ViewPersonalScheduleViewModel.class, new ViewPersonalScheduleViewModel());
        useCaseFactory.put(
                ViewPersonalScheduleUseCase.class,
                () -> new ViewPersonalScheduleController(
                        new ViewPersonalScheduleFxPresenter(getViewModel(ViewPersonalScheduleViewModel.class)),
                        shiftRepository,
                        employeeRepository,
                        authenticationService
                ));
    }

}
