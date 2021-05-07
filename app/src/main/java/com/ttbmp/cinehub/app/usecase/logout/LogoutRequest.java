package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public class LogoutRequest extends AuthenticatedRequest {

    public LogoutRequest(String sessionToken) {
        super(sessionToken);
    }

    @Override
    protected void onValidate() {

    }

}
