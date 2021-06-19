package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author Fabio Buracchi
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class ShiftListRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_START_TIME_ERROR = new Request.Error("Start time value can't be null");
    public static final Request.Error MISSING_END_TIME_ERROR = new Request.Error("End time value can't be null");
    public static final Request.Error INVALID_TIME_SELECTION_ERROR = new Request.Error("Start time value can't be after the end time");

    LocalDate start;
    LocalDate end;

    public ShiftListRequest(String sessionToken, LocalDate start, LocalDate end) {
        super(sessionToken);
        this.start = start;
        this.end = end;
    }

    @Override
    public void onValidate() {
        if (start == null || end == null) {
            if (start == null) {
                addError(MISSING_START_TIME_ERROR);
            }
            if (end == null) {
                addError(MISSING_END_TIME_ERROR);
            }
        } else if (start.isAfter(end)) {
            addError(INVALID_TIME_SELECTION_ERROR);
        }
    }

}
