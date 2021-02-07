package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import com.ttbmp.cinehub.app.client.desktop.CinehubApplication;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.HallRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftController;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class ManageShiftActivity extends Activity {

    private final ShiftRepository shiftRepository = CinehubApplication.APP_CONTAINER.getFactory(ShiftRepository.class).get();
    private final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private final HallRepository hallRepository = CinehubApplication.APP_CONTAINER.getFactory(HallRepository.class).get();

    public ManageShiftActivity() throws IOException {
        super(new ShowShiftView());
        viewModelStore.put(ManageEmployeesShiftViewModel.class, new ManageEmployeesShiftViewModel());
        useCaseFactory.put(
                ManageEmployeesShiftUseCase.class,
                () -> new ManageEmployeesShiftController(
                        new ManageEmployeesShiftFxPresenter(getViewModel(ManageEmployeesShiftViewModel.class)),
                        shiftRepository,
                        cinemaRepository,
                        hallRepository
                )
        );
    }

}
