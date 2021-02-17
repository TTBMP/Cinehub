package com.ttbmp.cinehub.app.usecase.login;

public interface LoginPresenter {

    void presentSessionToken(String sessionToken);

    void presentNullRequestException();

    void presentInvalidRequestException(LoginRequest request);

}
