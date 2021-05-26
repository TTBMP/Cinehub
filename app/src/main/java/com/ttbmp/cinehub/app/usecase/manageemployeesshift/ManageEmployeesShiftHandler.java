package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;

/**
 * @author Fabio Buracchi
 */
public class ManageEmployeesShiftHandler implements ManageEmployeesShiftUseCase {

    private final ManageEmployeesShiftController controller;

    public ManageEmployeesShiftHandler(ManageEmployeesShiftPresenter presenter) {
        controller = new ManageEmployeesShiftController(new ServiceLocator(), presenter);
    }

    @Override
    public void getCinemaList(GetCinemaListRequest request) {
        controller.getCinemaList(request);
    }

    @Override
    public void getEmployeeList(GetEmployeeListRequest request) {
        controller.getEmployeeList(request);
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        controller.getShiftList(request);
    }

    @Override
    public void createRepeatedShift(ShiftRepeatRequest request) {
        controller.createRepeatedShift(request);
    }

    @Override
    public void deleteShift(ShiftRequest request) {
        controller.deleteShift(request);
    }

    @Override
    public void createShift(CreateShiftRequest request) {
        controller.createShift(request);
    }

    @Override
    public void modifyShift(ShiftModifyRequest request) {
        controller.modifyShift(request);
    }

}
