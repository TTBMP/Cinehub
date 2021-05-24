package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public class RoleRequest extends AuthenticatedRequest {

    public RoleRequest(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

}
