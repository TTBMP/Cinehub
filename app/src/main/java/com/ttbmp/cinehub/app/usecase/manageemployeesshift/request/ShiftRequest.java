package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Massimo Mazzetti
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class ShiftRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift not existing.");
    public static final Request.Error INVALID_SHIFT = new Request.Error("Invalid Shift.");

    int shiftId;

    public ShiftRequest(String sessionToken, int shiftId) {
        super(sessionToken);
        this.shiftId = shiftId;
    }

    @Override
    protected void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }


}
