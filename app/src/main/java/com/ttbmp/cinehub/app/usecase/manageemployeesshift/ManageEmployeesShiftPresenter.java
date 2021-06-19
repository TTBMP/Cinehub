package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.service.security.SecurePresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;

/**
 * @author Massimo Mazzetti
 */
public interface ManageEmployeesShiftPresenter extends SecurePresenter {

    void presentShiftList(GetShiftListReply reply);

    void presentEmployeeList(GetEmployeeListReply reply);

    void presentCinemaList(GetCinemaListReply reply);

    void presentSaveShift();

    void presentDeleteShift();

    void presentRepeatShift(ShiftRepeatReply reply);

    void presentCreateShift(CreateShiftReply reply);

    void presentCreateShiftError(Throwable error);

    void presentModifyShiftError(Throwable error);

}
