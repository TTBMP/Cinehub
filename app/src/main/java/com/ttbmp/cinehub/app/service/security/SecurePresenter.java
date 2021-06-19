package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.utilities.presenter.Presenter;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

public interface SecurePresenter extends Presenter {

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e);

}
