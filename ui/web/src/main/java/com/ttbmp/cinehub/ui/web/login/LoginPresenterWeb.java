package com.ttbmp.cinehub.ui.web.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginRequest;
import com.ttbmp.cinehub.app.usecase.login.LoginResponse;
import org.springframework.ui.Model;

public class LoginPresenterWeb implements LoginPresenter {

    private final Model model;

    public LoginPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentSessionToken(LoginResponse response) {
        model.addAttribute("sessionToken", response.getSessionCookie());
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        // TODO
    }

    @Override
    public void presentNullRequestException() {
        // TODO
    }

    @Override
    public void presentInvalidRequestException(LoginRequest request) {
        // TODO
    }

}
