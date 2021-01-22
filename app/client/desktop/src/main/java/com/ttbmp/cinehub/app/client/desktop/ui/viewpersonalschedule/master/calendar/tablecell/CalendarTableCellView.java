package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master.calendar.tablecell;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellView extends FxmlView {

    public CalendarTableCellView() throws IOException {
        super("calendar_table_cell.fxml");
        addStylesheet("styles.css");
    }

    @Override
    public CalendarTableCellViewController getController() {
        return (CalendarTableCellViewController) super.getController();
    }
}
