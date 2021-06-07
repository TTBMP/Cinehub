package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Fabio Buracchi
 */
public class ShiftDetailView extends FxmlView {

    public ShiftDetailView() {
        super("view_personal_schedule/shift_detail.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

}
