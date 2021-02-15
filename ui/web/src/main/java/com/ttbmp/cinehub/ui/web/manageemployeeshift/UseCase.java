package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;

public class UseCase {

    private static ManageEmployeeShiftViewModel viewModel;
    public static final ManageEmployeesShiftUseCase manageEmployeeUseCase =
            new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(getViewModel()));

    private UseCase() {
    }

    public static ManageEmployeeShiftViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ManageEmployeeShiftViewModel();
        }
        return viewModel;
    }

}
