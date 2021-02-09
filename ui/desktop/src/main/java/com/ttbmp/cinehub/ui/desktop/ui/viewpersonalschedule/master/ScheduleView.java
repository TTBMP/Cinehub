package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ScheduleView extends FxmlView {

    public ScheduleView() throws IOException {
        super("view_personal_schedule/schedule.fxml");
        addStylesheet("styles.css");
        addStylesheet("personal_schedule.css");
    }

}
