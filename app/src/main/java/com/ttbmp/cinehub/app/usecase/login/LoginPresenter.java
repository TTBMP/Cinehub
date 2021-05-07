package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;

public interface LoginPresenter {

    void presentSessionToken(LoginResponse response);

    void presentAuthenticationError(SecurityException e);

    void presentNullRequestException();

    void presentInvalidRequestException(LoginRequest request);

}
