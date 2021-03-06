package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

/**
 * @author Massimo Mazzetti
 */
public class ManageShiftActivity extends Activity {

    public ManageShiftActivity() {
        super(new ShowShiftView());
        var viewModel = new ManageEmployeesShiftViewModel();
        var presenter = new ManageEmployeesShiftFxPresenter(viewModel);
        viewModelStore.put(ManageEmployeesShiftViewModel.class, viewModel);
        useCaseFactory.put(ManageEmployeesShiftUseCase.class, () -> new ManageEmployeesShiftHandler(presenter));
    }

}
