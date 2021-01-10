package com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.utilities.View;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellView extends View {

    public CalendarTableCellView() throws IOException {
        super("calendar_table_cell.fxml");
    }

    @Override
    public CalendarTableCellController getController() {
        return (CalendarTableCellController) super.getController();
    }
}
