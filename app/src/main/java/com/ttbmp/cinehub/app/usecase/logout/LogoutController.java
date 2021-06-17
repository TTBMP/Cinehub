package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.request.Request;

public class LogoutController implements LogoutUseCase {

    private final LogoutPresenter presenter;

    @SuppressWarnings("unused")
    public LogoutController(ServiceLocator serviceLocator, LogoutPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout(LogoutRequest request) {
        try {
            Request.validate(request);
            presenter.logout();
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        }
    }

}
