package com.ttbmp.cinehub.ui.desktop.ui.manageshift;

import com.ttbmp.cinehub.domain.repository.CinemaRepository;
import com.ttbmp.cinehub.domain.repository.HallRepository;
import com.ttbmp.cinehub.domain.repository.ShiftRepository;
import com.ttbmp.cinehub.domain.service.email.EmailService;
import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.ManageEmployeesShiftController;
import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class ManageShiftActivity extends Activity {

    private final ShiftRepository shiftRepository = CinehubApplication.APP_CONTAINER.getFactory(ShiftRepository.class).get();
    private final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private final HallRepository hallRepository = CinehubApplication.APP_CONTAINER.getFactory(HallRepository.class).get();
    private final EmailService emailService = CinehubApplication.APP_CONTAINER.getFactory(EmailService.class).get();

    public ManageShiftActivity() throws IOException {
        super(new ShowShiftView());
        viewModelStore.put(ManageEmployeesShiftViewModel.class, new ManageEmployeesShiftViewModel());
        useCaseFactory.put(
                ManageEmployeesShiftUseCase.class,
                () -> new ManageEmployeesShiftController(
                        new ManageEmployeesShiftFxPresenter(getViewModel(ManageEmployeesShiftViewModel.class)),
                        shiftRepository,
                        cinemaRepository,
                        hallRepository,
                        emailService
                )
        );
    }

}
