package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Fabio Buracchi
 */
public class ScheduleView extends FxmlView {

    public ScheduleView() {
        super("view_personal_schedule/schedule.fxml");
        addStylesheet("theme.css");
        addStylesheet("styles.css");
        addStylesheet("personal_schedule.css");
    }

}
