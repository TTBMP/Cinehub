package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;


public class GetEmployeeListRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_CINEMA = new Request.Error("Cinema non valido");

    private CinemaDto cinema;

    public GetEmployeeListRequest(String sessionToken, CinemaDto cinema) {
        super(sessionToken);
        this.cinema = cinema;
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    @Override
    protected void onValidate() {
        if (cinema == null) {
            addError(MISSING_CINEMA);
        }
    }
}
