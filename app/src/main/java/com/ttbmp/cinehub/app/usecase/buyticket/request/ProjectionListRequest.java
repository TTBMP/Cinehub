package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class ProjectionListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");
    public static final Request.Error INVALID_MOVIE = new Request.Error("Movie can't be null");
    public static final Request.Error INVALID_CINEMA = new Request.Error("Cinema can't be null");

    Integer movieId;
    Integer cinemaId;
    String date;

    @Override
    public void onValidate() {
        if (date == null) {
            addError(MISSING_DATE_ERROR);
        }
        if (date != null) {
            var localDate = LocalDate.parse(this.date);
            if (localDate.isBefore(LocalDate.now())) {
                addError(MISSING_DATE_ERROR);
            }
        }
    }

}
