package com.ttbmp.cinehub.core.presenter;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.util.List;

public interface ViewPersonalSchedulePresenter {

    void presentShiftList(Result<List<Shift>> result);

}
