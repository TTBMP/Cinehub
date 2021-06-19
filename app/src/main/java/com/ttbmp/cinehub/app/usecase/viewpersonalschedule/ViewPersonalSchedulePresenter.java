package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.service.security.SecurePresenter;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ShiftListReply;

/**
 * @author Fabio Buracchi
 */
public interface ViewPersonalSchedulePresenter extends SecurePresenter {

    void presentGetShiftList(ShiftListReply result);

    void presentGetProjectionList(ProjectionListReply result);

}
