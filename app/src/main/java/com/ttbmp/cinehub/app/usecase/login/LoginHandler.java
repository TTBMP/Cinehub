package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.di.ServiceLocator;

public class LoginHandler  implements LoginUseCase {

    private final LoginController controller;

    public LoginHandler(LoginPresenter presenter) {
        controller = new LoginController(new ServiceLocator(), presenter);
    }


    @Override
    public void login(LoginRequest request) {
        controller.login(request);
    }
}
