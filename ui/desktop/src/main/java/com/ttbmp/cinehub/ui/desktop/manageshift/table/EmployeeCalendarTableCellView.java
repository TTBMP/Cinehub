package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeCalendarTableCellView extends FxmlView {

    public EmployeeCalendarTableCellView() {
        super("calendar_employee_item.fxml");

    }

    @Override
    public CalendarEmployeeItemViewController getController() {
        return (CalendarEmployeeItemViewController) super.getController();

    }

}
