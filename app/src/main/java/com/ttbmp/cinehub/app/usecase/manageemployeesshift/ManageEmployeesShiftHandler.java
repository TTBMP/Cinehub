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
    public void getCinemaList() {
        controller.getCinemaList();
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
    public void saveRepeatedShift(ShiftRepeatRequest request) {
        controller.saveRepeatedShift(request);
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
