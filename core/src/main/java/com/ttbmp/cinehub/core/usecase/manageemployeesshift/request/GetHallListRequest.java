package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.usecase.Request;


/**
 * @author Massimo Mazzetti
 */

public class GetHallListRequest extends Request {
    public static final Request.Error MISSING_HALL = new Request.Error("sala non valida");

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
    public void onValidate() {
        if (cinema == null) {
            addError(MISSING_HALL);
        }
    }
}
