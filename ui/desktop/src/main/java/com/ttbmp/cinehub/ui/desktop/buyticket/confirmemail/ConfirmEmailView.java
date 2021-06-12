package com.ttbmp.cinehub.ui.desktop.buyticket.confirmemail;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class ConfirmEmailView extends FxmlView {

    public ConfirmEmailView() {
        super("buy_ticket/confirm_email.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
