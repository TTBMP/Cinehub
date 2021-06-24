package com.ttbmp.cinehub.ui.web.logout;

import com.ttbmp.cinehub.app.usecase.logout.LogoutPresenter;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

public class LogoutPresenterWeb extends PresenterWeb implements LogoutPresenter {

    public LogoutPresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void logout() {
        // No operations.
    }

}
