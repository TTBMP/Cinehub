package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Ivan Palmieri
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class SeatListRequest extends AuthenticatedRequest {

    public static final Request.Error PROJECTION_ERROR = new Request.Error("Projection id can't be null");

    int projectionId;

    public SeatListRequest(String sessionToken, int projectionId) {
        super(sessionToken);
        this.projectionId = projectionId;
    }

    @Override
    public void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

}
