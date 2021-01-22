package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class PersonalScheduleView extends FxmlView {

    public PersonalScheduleView() throws IOException {
        super("personal_schedule.fxml");
        addStylesheet("styles.css");
    }

}
