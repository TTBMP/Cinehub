package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.ServiceLocator;

public class ViewPersonalScheduleHandler implements ViewPersonalScheduleUseCase {

    private final ViewPersonalScheduleController controller;

    public ViewPersonalScheduleHandler(ViewPersonalSchedulePresenter presenter) {
        controller = new ViewPersonalScheduleController(new ServiceLocator(), presenter);
    }

    @Override
    public void getShiftList(ShiftListRequest request) {
        controller.getShiftList(request);
    }

    @Override
    public void getShiftProjectionList(ProjectionListRequest request) {
        controller.getShiftProjectionList(request);
    }

}
