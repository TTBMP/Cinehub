package com.ttbmp.cinehub.ui.desktop.manageshift.table;


import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.TableCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class EmployeeCalendarTableCell extends TableCell<EmployeeShiftWeek, EmployeeDto> {
    private final Activity activity;
    private final NavController navController;

    public EmployeeCalendarTableCell(Activity activity, NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    protected void updateItem(EmployeeDto employeeDto, boolean empty) {
        super.updateItem(employeeDto, empty);
        if (employeeDto != null) {
            EmployeeCalendarTableCellView item = null;
            try {
                item = new EmployeeCalendarTableCellView();
                item.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, employeeDto);
            setGraphic(item.getRoot());
        }
    }

}
