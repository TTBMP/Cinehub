package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public interface GetUserRolePresenter {

    void present(RoleResponse response);

    void presentInvalidRequest(RoleRequest request);

    void presentNullRequest();

    void presentRepositoryError(RepositoryException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
