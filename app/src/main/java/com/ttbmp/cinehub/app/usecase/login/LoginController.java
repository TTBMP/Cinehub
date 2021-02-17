package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationException;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.utilities.request.Request;

public class LoginController implements LoginUseCase {

    private final LoginPresenter presenter;
    private final AuthenticationService authenticationService;

    public LoginController(ServiceLocator serviceLocator, LoginPresenter presenter) {
        this.presenter = presenter;
        this.authenticationService = serviceLocator.getService(AuthenticationService.class);
    }

    @Override
    public void login(LoginRequest request) {
        try {
            Request.validate(request);
            String sessionToken = authenticationService.logIn(request.getUsername(), request.getUsername());
            presenter.presentSessionToken(sessionToken);
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequestException();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequestException(request);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

}
