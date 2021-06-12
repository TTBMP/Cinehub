package com.ttbmp.cinehub.ui.desktop.manageshift.detail;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Massimo Mazzetti
 */
public class ShowShiftDetailView extends FxmlView {

    public ShowShiftDetailView() {
        super("manage_employee_shift/show_detail_shift.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}






