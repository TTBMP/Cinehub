package com.ttbmp.cinehub.core.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.core.usecase.UseCase;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalScheduleUseCase extends UseCase {

    void getShiftList(GetShiftListRequest request);

}
