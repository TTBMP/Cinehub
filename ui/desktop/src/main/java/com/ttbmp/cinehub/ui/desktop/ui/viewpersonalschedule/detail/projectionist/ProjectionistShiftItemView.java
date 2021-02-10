package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftItemView extends FxmlView {

    public ProjectionistShiftItemView() throws IOException {
        super("view_personal_schedule/projectionist_shift_item.fxml");
        addStylesheet("styles.css");
    }

    @Override
    public ProjectionistShiftItemViewController getController() {
        return (ProjectionistShiftItemViewController) super.getController();
    }

}
