package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.utilities.request.Request;

public interface LoginPresenter {

    void presentSessionToken(LoginResponse response);

    void presentInvalidRequestException(Request request);

    void presentNullRequestException();

    void presentSecurityError(SecurityException e);

}
