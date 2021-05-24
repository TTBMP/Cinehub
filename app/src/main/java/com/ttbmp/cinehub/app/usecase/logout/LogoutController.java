package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.Request;

public class LogoutController implements LogoutUseCase {

    private final LogoutPresenter presenter;
    private final SecurityService securityService;

    public LogoutController(ServiceLocator serviceLocator, LogoutPresenter presenter) {
        this.presenter = presenter;
        this.securityService = serviceLocator.getService(SecurityService.class);
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
