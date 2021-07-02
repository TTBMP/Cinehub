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
public class CinemaListRequest extends Request {

    public static final Request.Error DATE_ERROR = new Request.Error("The date cannot be earlier than today");
    public static final Request.Error MOVIE_ERROR = new Request.Error("Cinema can't be null");

    Integer movieId;
    String date;

    @Override
    public void onValidate() {
        if (date == null) {
            addError(DATE_ERROR);
        }
        if (date != null) {
            var localDate = LocalDate.parse(date);
            if (localDate.isBefore(LocalDate.now())) {
                addError(DATE_ERROR);
            }
        }
    }


}
