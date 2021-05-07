package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.di.ServiceLocator;

public class LogoutHandler implements LogoutUseCase {

    private final LogoutController controller;

    public LogoutHandler(LogoutPresenter presenter) {
        controller = new LogoutController(new ServiceLocator(), presenter);
    }

    @Override
    public void logout(LogoutRequest request) {
        controller.logout(request);
    }

}
