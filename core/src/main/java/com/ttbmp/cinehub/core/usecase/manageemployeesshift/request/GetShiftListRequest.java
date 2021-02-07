package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.usecase.Request;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */

public class GetShiftListRequest extends Request {
    public static final Request.Error MISSING_CINEMA = new Request.Error("Cinema non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");

    private CinemaDto cinema;
    private LocalDate start;


    public GetShiftListRequest(LocalDate start, CinemaDto cinema) {
        this.start = start;
        this.cinema = cinema;
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    @Override
    protected void onValidate() {
        if (cinema == null) {
            addError(MISSING_CINEMA);
        }
        if (start == null) {
            addError(MISSING_START);
        }
    }
}
