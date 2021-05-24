package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public class GetCinemaListRequest extends AuthenticatedRequest {

    public GetCinemaListRequest(String sessionToken) {
        super(sessionToken);
    }

    @Override
    protected void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

}
