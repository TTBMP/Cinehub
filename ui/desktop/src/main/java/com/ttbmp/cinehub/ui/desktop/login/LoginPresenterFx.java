package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.usecase.login.LoginPresenter;
import com.ttbmp.cinehub.app.usecase.login.LoginRequest;

public class LoginPresenterFx implements LoginPresenter {

    private final LoginViewModel viewModel;

    public LoginPresenterFx(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentNullRequestException() {
        viewModel.accessErrorProperty().setValue("Error with access");
    }

    @Override
    public void presentInvalidRequestException(LoginRequest request) {
        if(request.getErrorList().contains(LoginRequest.MISSING_PASSWORD_ERROR)){
            viewModel.accessErrorProperty().setValue(LoginRequest.MISSING_PASSWORD_ERROR.getMessage());
        }
        if(request.getErrorList().contains(LoginRequest.MISSING_USERNAME_ERROR)){
            viewModel.accessErrorProperty().setValue(LoginRequest.MISSING_USERNAME_ERROR.getMessage());
        }

    }
}
