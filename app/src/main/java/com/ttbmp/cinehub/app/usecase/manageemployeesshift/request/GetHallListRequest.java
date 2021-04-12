package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.usecase.Request;


/**
 * @author Massimo Mazzetti
 */
public class GetHallListRequest extends Request {

    public static final Request.Error MISSING_HALL = new Request.Error("sala non valida");

    private Integer cinemaId;

    public GetHallListRequest(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public void onValidate() {
        if (cinemaId == null) {
            addError(MISSING_HALL);
        }
    }

}
