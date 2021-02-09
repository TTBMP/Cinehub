package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.detail;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ShiftDetailView extends FxmlView {

    public ShiftDetailView() throws IOException {
        super("view_personal_schedule/shift_detail.fxml");
        addStylesheet("styles.css");
    }

}
