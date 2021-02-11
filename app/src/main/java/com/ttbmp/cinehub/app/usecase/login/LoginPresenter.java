package com.ttbmp.cinehub.app.usecase.login;

public interface LoginPresenter {
    void presentNullRequestException();

    void presentInvalidRequestException(LoginRequest request);
}
