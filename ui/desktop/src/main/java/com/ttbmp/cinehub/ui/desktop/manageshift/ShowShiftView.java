package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Massimo Mazzetti
 */
public class ShowShiftView extends FxmlView {

    protected ShowShiftView() {
        super("manage_employee_shift/show_shift.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
