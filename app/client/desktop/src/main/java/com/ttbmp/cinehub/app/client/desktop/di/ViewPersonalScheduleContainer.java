package com.ttbmp.cinehub.app.client.desktop.di;

import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleFxPresenter;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.core.usecase.ViewPersonalScheduleUseCase;

import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleContainer {

    private final ViewPersonalScheduleViewModel viewModel = new ViewPersonalScheduleViewModel();

    private final ShiftRepository shiftRepository;

    private final AuthenticationService authenticationService;

    public ViewPersonalScheduleContainer(ShiftRepository shiftRepository, AuthenticationService authenticationService) {
        Objects.requireNonNull(shiftRepository);
        this.shiftRepository = shiftRepository;
        this.authenticationService = authenticationService;
    }

    public ViewPersonalScheduleViewModel getViewModel() {
        return viewModel;
    }

    public ViewPersonalScheduleUseCase getUseCase() {
        return new ViewPersonalScheduleUseCase(
                new ViewPersonalScheduleFxPresenter(viewModel),
                shiftRepository,
                authenticationService
        );
    }

}
