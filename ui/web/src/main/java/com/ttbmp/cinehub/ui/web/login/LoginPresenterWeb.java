package com.ttbmp.cinehub.ui.web.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginReply;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

public class LoginPresenterWeb extends PresenterWeb implements LoginPresenter {

    public LoginPresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void presentSessionToken(LoginReply reply) {
        model.addAttribute("sessionToken", reply.getSessionCookie());
    }

    @Override
    public void presentSecurityError(SecurityException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

}
