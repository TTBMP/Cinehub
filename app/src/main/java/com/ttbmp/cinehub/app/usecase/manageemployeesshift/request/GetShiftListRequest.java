package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Cinema;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
public class GetShiftListRequest extends AuthenticatedRequest {
    public static final Request.Error INVALID_CINEMA = new Request.Error("Cinema non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Fine non valido");

    private int cinemaId;
    private LocalDate start;
    private LocalDate end;

    public GetShiftListRequest(String sessionToken, int cinemaId, LocalDate start, LocalDate end) {
        super(sessionToken);
        this.cinemaId = cinemaId;
        this.start = start;
        this.end = end;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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
        if (end == null) {
            addError(MISSING_END);
        }
    }

    public void semanticValidate(Cinema cinema) throws InvalidRequestException {
        if (cinema == null) {
            addError(INVALID_CINEMA);
            throw new InvalidRequestException();
        }
    }
}
