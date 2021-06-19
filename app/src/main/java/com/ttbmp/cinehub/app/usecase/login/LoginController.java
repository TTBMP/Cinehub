package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Fabio Buracchi
 */
public class LoginController implements LoginUseCase {

    private final LoginPresenter presenter;
    private final SecurityService securityService;

    public LoginController(ServiceLocator serviceLocator, LoginPresenter presenter) {
        this.presenter = presenter;
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    @Override
    public void login(LoginRequest request) {
        execute(presenter, request, () -> {
            try {
                Request.validate(request);
                var sessionToken = securityService.authenticate(request.getUsername(), request.getPassword());
                presenter.presentSessionToken(new LoginReply(sessionToken));
            } catch (SecurityException e) {
                presenter.presentSecurityError(e);
            }
        });
    }

}
