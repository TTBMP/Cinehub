package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.usecase.Request;


/**
 * @author Massimo Mazzetti
 */
public class GetHallListRequest extends Request {

    public static final Request.Error MISSING_HALL = new Request.Error("sala non valida");

    private int cinemaId;

    public GetHallListRequest(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public void onValidate() {
        if (cinemaId == -1) {
            addError(MISSING_HALL);
        }
    }

}
