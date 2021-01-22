package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master.calendar.tablecell.shiftitem;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() throws IOException {
        super("calendar_shift_item.fxml");
        addStylesheet("styles.css");
    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
