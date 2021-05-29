package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.usecase.login.LoginHandler;
import com.ttbmp.cinehub.app.usecase.login.LoginUseCase;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

public class LoginActivity extends Activity {

    public LoginActivity() {
        super(new LoginView());
        var viewModel = new LoginViewModel();
        var presenter = new LoginPresenterFx(viewModel);
        viewModelStore.put(LoginViewModel.class, viewModel);
        useCaseFactory.put(LoginUseCase.class, () -> new LoginHandler(presenter));
    }

}
