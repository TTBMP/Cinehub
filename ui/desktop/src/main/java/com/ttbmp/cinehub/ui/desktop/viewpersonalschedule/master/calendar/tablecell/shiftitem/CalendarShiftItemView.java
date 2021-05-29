package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.tablecell.shiftitem;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() {
        super("view_personal_schedule/calendar_shift_item.fxml");
        addStylesheet("styles.css");
    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
