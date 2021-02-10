package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftDetailView extends FxmlView {

    public ProjectionistShiftDetailView() throws IOException {
        super("view_personal_schedule/projectionist_shift_detail.fxml");
        addStylesheet("styles.css");
    }

}
