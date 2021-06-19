package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = false)
public class MovieListRequest extends Request {

    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("The date cannot be earlier than today");

    LocalDate date;

    @Override
    public void onValidate() {
        if (date == null) {
            addError(MISSING_DATE_ERROR);
        }
        if (date != null) {
            var condition = date.isBefore(LocalDate.now());
            if (condition) {
                addError(MISSING_DATE_ERROR);
            }
        }

    }

}
