package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.UseCase;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalScheduleUseCase extends UseCase {

    void getShiftList(GetShiftListRequest request);

}
