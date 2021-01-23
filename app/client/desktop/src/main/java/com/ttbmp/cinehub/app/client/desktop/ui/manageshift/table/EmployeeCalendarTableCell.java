package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.core.dto.EmployeeDto;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Massimo Mazzetti
 */


public class EmployeeCalendarTableCell extends TableCell<EmployeeShiftWeek, EmployeeDto> {
    private final Activity activity;
    private final NavController navController;

    public EmployeeCalendarTableCell(TableColumn<EmployeeShiftWeek, EmployeeDto> employeeShiftWeekStringTableColumn, Activity activity, NavController navController) {
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
