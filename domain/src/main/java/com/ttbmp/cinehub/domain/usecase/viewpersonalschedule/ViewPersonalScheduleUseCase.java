package com.ttbmp.cinehub.domain.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.domain.usecase.UseCase;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalScheduleUseCase extends UseCase {

    void getShiftList(GetShiftListRequest request);

}
