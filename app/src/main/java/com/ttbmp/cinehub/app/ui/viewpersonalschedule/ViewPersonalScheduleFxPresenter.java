package com.ttbmp.cinehub.app.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.presenter.ViewPersonalSchedulePresenter;
import com.ttbmp.cinehub.core.utilities.result.Result;
import com.ttbmp.cinehub.core.utilities.result.ResultObserver;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleFxPresenter implements ViewPersonalSchedulePresenter {

    private final ViewPersonalScheduleViewModel viewModel;

    public ViewPersonalScheduleFxPresenter(ViewPersonalScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentShiftList(Result<List<Shift>> result) {
        result.addObserver(new ResultObserver<>() {
            @Override
            public void onSuccess(List<Shift> shiftList) {
                viewModel.getShiftList().clear();
                viewModel.getShiftList().addAll(ShiftDataMapper.mapToDtoList(shiftList));
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }
}
