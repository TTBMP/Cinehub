package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.utilities.request.Request;

public interface LogoutPresenter {

    void logout();

    void presentInvalidRequest(Request request);

    void presentNullRequest();

}
