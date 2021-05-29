package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.tablecell;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellView extends FxmlView {

    public CalendarTableCellView() {
        super("view_personal_schedule/calendar_table_cell.fxml");
        addStylesheet("styles.css");
    }

    @Override
    public CalendarTableCellViewController getController() {
        return (CalendarTableCellViewController) super.getController();
    }
}
