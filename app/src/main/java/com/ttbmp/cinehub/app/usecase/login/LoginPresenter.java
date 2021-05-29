package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.utilities.request.Request;

public interface LoginPresenter {

    void presentSessionToken(LoginResponse response);

    void presentInvalidRequest(Request request);

    void presentNullRequest();

    void presentSecurityError(SecurityException e);

}
