package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.client.desktop.CinehubApplication;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.core.usecase.ViewPersonalScheduleUseCase;

import java.io.IOException;

public class ViewPersonalScheduleActivity extends Activity {

    private final ShiftRepository shiftRepository = CinehubApplication.APP_CONTAINER.getFactory(ShiftRepository.class).get();
    private final AuthenticationService authenticationService = CinehubApplication.APP_CONTAINER.getFactory(AuthenticationService.class).get();

    public ViewPersonalScheduleActivity() throws IOException {
        super(new PersonalScheduleView());
        viewModelStore.put(ViewPersonalScheduleViewModel.class, new ViewPersonalScheduleViewModel());
        useCaseFactory.put(
                ViewPersonalScheduleUseCase.class,
                () -> new ViewPersonalScheduleUseCase(
                        new ViewPersonalScheduleFxPresenter(getViewModel(ViewPersonalScheduleViewModel.class)),
                        shiftRepository,
                        authenticationService
                ));
    }

}
