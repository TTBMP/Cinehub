package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

public class GetEmployeeListRequest extends Request{

    public static final Request.Error MISSING_CINEMA = new Request.Error("Cinema non valido");

    private CinemaDto cinema;
    private LocalDate date;

    public GetEmployeeListRequest() {
    }

    public GetEmployeeListRequest(CinemaDto cinema, LocalDate date) {
        this.cinema = cinema;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
