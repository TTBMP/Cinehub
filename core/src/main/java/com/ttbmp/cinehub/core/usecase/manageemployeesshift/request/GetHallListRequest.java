package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.utilities.notification.Notification;

/**
 * @author Massimo Mazzetti
 */

public class GetHallListRequest extends Request {
    public static final Notification.Error MISSING_HALL = new Notification.Error("sala non valida");
    private Cinema cinema;

    public GetHallListRequest(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void validate() {
        if (cinema == null) {
            notification.addError(MISSING_HALL);
        }
    }
}
