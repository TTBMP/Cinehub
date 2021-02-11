package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.usecase.Request;

public class LoginController implements  LoginUseCase{

    private final LoginPresenter loginPresenter;
    private final AuthenticationService authenticationService;

    public LoginController(
            ServiceLocator serviceLocator, LoginPresenter loginPresenter
    ) {
        this.loginPresenter = loginPresenter;
        this.authenticationService = serviceLocator.getService(AuthenticationService.class);
    }

    @Override
    public void login(LoginRequest request) {
        try {
            Request.validate(request);
            authenticationService.sigIn(request.getUsername(),request.getUsername());
        } catch (Request.NullRequestException e) {
            loginPresenter.presentNullRequestException();
        } catch (Request.InvalidRequestException e) {
            loginPresenter.presentInvalidRequestException(request);
        }
    }
}
