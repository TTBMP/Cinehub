package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

public class LoginView extends FxmlView {

    protected LoginView() {
        super("login/login.fxml");
        addStylesheet("theme.css");
        addStylesheet("stylesLogin.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
