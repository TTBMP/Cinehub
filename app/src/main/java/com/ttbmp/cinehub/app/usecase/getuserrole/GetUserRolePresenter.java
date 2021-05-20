package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public interface GetUserRolePresenter {

    void present(RoleResponse response);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
