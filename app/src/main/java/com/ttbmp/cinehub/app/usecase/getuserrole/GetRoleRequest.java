package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public class GetRoleRequest extends AuthenticatedRequest {

    public GetRoleRequest(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public void onValidate() {

    }

}
