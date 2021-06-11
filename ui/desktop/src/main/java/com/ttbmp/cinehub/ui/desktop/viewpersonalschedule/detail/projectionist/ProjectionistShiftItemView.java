package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftItemView extends FxmlView {

    public ProjectionistShiftItemView() {
        super("view_personal_schedule/projectionist_shift_item.fxml");
        addStylesheet("theme.css");
    }

    @Override
    public ProjectionistShiftItemViewController getController() {
        return (ProjectionistShiftItemViewController) super.getController();
    }

}
