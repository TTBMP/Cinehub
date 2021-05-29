package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginResponse;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;

import java.util.stream.Collectors;

public class LoginPresenterFx implements LoginPresenter {

    private final LoginViewModel viewModel;

    public LoginPresenterFx(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSessionToken(LoginResponse response) {
        CinehubApplication.setSessionToken(response.getSessionCookie());
        viewModel.setIsLogged(true);
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

    @Override
    public void presentNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.setErrorMessage(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }

}
