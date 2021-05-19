package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

public class MovieListRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");

    private LocalDate date;

    public MovieListRequest(String sessionToken, LocalDate date) {
        super(sessionToken);
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
        if (date == null) {
            addError(MISSING_DATE_ERROR);
        }
    }

}
