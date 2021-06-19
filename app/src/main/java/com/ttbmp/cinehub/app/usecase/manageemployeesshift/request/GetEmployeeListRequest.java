package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class GetEmployeeListRequest extends AuthenticatedRequest {

    public static final Request.Error INVALID_CINEMA = new Request.Error("Cinema non valido");

    int cinemaId;

    public GetEmployeeListRequest(String sessionToken, int cinemaId) {
        super(sessionToken);
        this.cinemaId = cinemaId;
    }

    @Override
    protected void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

}
