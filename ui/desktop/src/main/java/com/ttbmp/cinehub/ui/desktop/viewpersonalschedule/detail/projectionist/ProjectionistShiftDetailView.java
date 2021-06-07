package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftDetailView extends FxmlView {

    public ProjectionistShiftDetailView() {
        super("view_personal_schedule/projectionist_shift_detail.fxml");
        addStylesheet("theme.css");
        addStylesheet("styles.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

}
