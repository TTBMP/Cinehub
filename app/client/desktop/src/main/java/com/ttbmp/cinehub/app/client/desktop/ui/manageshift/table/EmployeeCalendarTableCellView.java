package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeCalendarTableCellView extends FxmlView {

    public EmployeeCalendarTableCellView() throws IOException {
        super("calendar_employee_item.fxml");

    }

    @Override
    public CalendarEmployeeItemViewController getController() {
        return (CalendarEmployeeItemViewController) super.getController();

    }

}
