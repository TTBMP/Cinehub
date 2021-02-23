package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.usecase.Request;

public class GetCinemaRequest extends Request {

    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");

    private final int idCinema;

    public GetCinemaRequest(int idCinema) {
        this.idCinema = idCinema;
    }

    public int getIdCinema() {
        return idCinema;
    }

    @Override
    protected void onValidate() {
        if(idCinema<0){
            addError(MISSING_CINEMA_ERROR);
        }
    }
}
