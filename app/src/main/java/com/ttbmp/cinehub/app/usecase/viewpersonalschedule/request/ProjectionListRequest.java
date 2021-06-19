package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Fabio Buracchi
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProjectionListRequest extends AuthenticatedRequest {

    public static final Request.Error INVALID_SHIFT_ID_ERROR = new Request.Error("Shift id can't be negative");

    int projectionistShiftId;

    public ProjectionListRequest(String sessionToken, int projectionistShiftId) {
        super(sessionToken);
        this.projectionistShiftId = projectionistShiftId;
    }

    @Override
    protected void onValidate() {
        if (projectionistShiftId < 0) {
            addError(INVALID_SHIFT_ID_ERROR);
        }
    }

}
