package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.utilities.presenter.Presenter;

public interface LoginPresenter extends Presenter {

    void presentSessionToken(LoginReply reply);

    void presentSecurityError(SecurityException e);

}
