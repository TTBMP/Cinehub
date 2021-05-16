package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
public class GetShiftListRequest extends Request {
    public static final Request.Error MISSING_CINEMA = new Request.Error("Cinema non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Fine non valido");

    private CinemaDto cinema;
    private LocalDate start;
    private LocalDate end;

    public GetShiftListRequest() {
    }

    public GetShiftListRequest(CinemaDto cinema, LocalDate start, LocalDate end) {
        this.cinema = cinema;
        this.start = start;
        this.end = end;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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
        if (end == null) {
            addError(MISSING_END);
        }
    }

}
