package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.utilities.request.Request;


/**
 * @author Massimo Mazzetti
 */
public class GetHallListRequest extends Request {

    public static final Request.Error MISSING_HALL = new Request.Error("sala non valida");

    private CinemaDto cinema;

    public GetHallListRequest(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    @Override
    public void onValidate() {
        if (cinema == null) {
            addError(MISSING_HALL);
        }
    }

}
