package com.ttbmp.cinehub.app.usecase.logout;

public interface LogoutPresenter {

    void logout();

    void presentInvalidRequest(LogoutRequest request);

    void presentNullRequest();

}
