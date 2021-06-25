package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class GetShiftListRequest extends AuthenticatedRequest {

    public static final Request.Error INVALID_CINEMA = new Request.Error("Invalid Cinema.");
    public static final Request.Error MISSING_START = new Request.Error("Invalid start.");
    public static final Request.Error MISSING_END = new Request.Error("Invalid end.");

    private int cinemaId;
    private LocalDate start;
    private LocalDate end;

    public GetShiftListRequest(String sessionToken, int cinemaId, LocalDate start, LocalDate end) {
        super(sessionToken);
        this.cinemaId = cinemaId;
        this.start = start;
        this.end = end;
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

}
