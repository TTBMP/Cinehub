package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ShiftListRequest;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalScheduleUseCase extends UseCase {

    void getShiftList(ShiftListRequest request);

    void getShiftProjectionList(ProjectionListRequest request);

}
