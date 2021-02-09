package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class PersonalScheduleView extends FxmlView {

    public PersonalScheduleView() throws IOException {
        super("personal_schedule.fxml");
        addStylesheet("styles.css");
        addStylesheet("personal_schedule.css");
    }

}
