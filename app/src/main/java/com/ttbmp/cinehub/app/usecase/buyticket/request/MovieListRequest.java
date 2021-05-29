package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

public class MovieListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");

    private LocalDate date;

    public MovieListRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void onValidate() {
        if (date.isBefore(LocalDate.now())) {
            addError(MISSING_DATE_ERROR);
        }
    }

}
