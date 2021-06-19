package com.ttbmp.cinehub.ui.web.login;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginReply;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.ui.Model;

public class LoginPresenterWeb implements LoginPresenter {

    private final Model model;

    public LoginPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentSessionToken(LoginReply reply) {
        model.addAttribute("sessionToken", reply.getSessionCookie());
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidRequest(Request request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {

    }

}
