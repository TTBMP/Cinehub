package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginRequest;
import com.ttbmp.cinehub.app.usecase.login.LoginResponse;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;

public class LoginPresenterFx implements LoginPresenter {

    private final LoginViewModel viewModel;

    public LoginPresenterFx(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSessionToken(LoginResponse response) {
        CinehubApplication.setSessionToken(response.getSessionCookie());
        viewModel.accessErrorProperty().setValue("");
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        viewModel.accessErrorProperty().setValue(e.getMessage());
    }

    @Override
    public void presentNullRequestException() {
        viewModel.accessErrorProperty().setValue("Error with access");
    }

    @Override
    public void presentInvalidRequestException(LoginRequest request) {
        if (request.getErrorList().contains(LoginRequest.MISSING_PASSWORD_ERROR)) {
            viewModel.accessErrorProperty().setValue(LoginRequest.MISSING_PASSWORD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(LoginRequest.MISSING_USERNAME_ERROR)) {
            viewModel.accessErrorProperty().setValue(LoginRequest.MISSING_USERNAME_ERROR.getMessage());
        }

    }

}
