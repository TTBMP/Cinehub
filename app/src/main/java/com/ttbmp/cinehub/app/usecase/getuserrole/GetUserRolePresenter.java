package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

public interface GetUserRolePresenter {

    void present(RoleReply reply);

    void presentInvalidRequest(Request request);

    void presentNullRequest();

    void presentRepositoryError(RepositoryException e);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
