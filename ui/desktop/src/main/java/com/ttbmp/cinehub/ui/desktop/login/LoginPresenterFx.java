package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginReply;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;

import java.util.stream.Collectors;

public class LoginPresenterFx implements LoginPresenter {

    private final LoginViewModel viewModel;

    public LoginPresenterFx(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSessionToken(LoginReply reply) {
        CinehubApplication.setSessionToken(reply.getSessionCookie());
        viewModel.loggedProperty().set(true);
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

    @Override
    public void presentNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.errorMessageProperty().setValue(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }

    @Override
    public void presentApplicationError(CinehubException e) {
        viewModel.errorMessageProperty().setValue(e.getMessage());
    }

}
