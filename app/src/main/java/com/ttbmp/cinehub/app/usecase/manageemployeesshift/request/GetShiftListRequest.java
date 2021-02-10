package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
public class GetShiftListRequest extends Request {
    public static final Request.Error MISSING_CINEMA = new Request.Error("Cinema non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");

    private LocalDate start;
    private int cinemaId;

    public GetShiftListRequest(LocalDate start, int cinemaId) {
        this.start = start;
        this.cinemaId = cinemaId;
    }

    public GetShiftListRequest() {

    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }



    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    @Override
    protected void onValidate() {

        if (start == null) {
            addError(MISSING_START);
        }
    }

}
