package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Cinema;


public class GetEmployeeListRequest extends AuthenticatedRequest {

    public static final Request.Error INVALID_CINEMA = new Request.Error("Cinema non valido");

    private int cinemaId;

    public GetEmployeeListRequest(String sessionToken, int cinemaId) {
        super(sessionToken);
        this.cinemaId = cinemaId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    protected void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

    public void semanticValidate(Cinema cinema) throws InvalidRequestException{
        if(cinema == null){
            addError(INVALID_CINEMA);
            throw new InvalidRequestException();
        }
    }
}
