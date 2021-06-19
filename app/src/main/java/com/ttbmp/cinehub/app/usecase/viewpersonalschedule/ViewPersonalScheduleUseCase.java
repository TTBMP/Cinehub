package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request.ShiftListRequest;
import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalScheduleUseCase extends UseCase {

    void getShiftList(ShiftListRequest request);

    void getShiftProjectionList(ProjectionListRequest request);

}
