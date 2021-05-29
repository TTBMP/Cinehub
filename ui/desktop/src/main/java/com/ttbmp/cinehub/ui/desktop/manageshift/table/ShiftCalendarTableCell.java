package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.TableCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class ShiftCalendarTableCell extends TableCell<EmployeeShiftWeek, Day> {
    private final Activity activity;
    private final NavController navController;

    public ShiftCalendarTableCell(Activity activity, NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    protected void updateItem(Day day, boolean empty) {
        super.updateItem(day, empty);
        if (day != null) {
            CalendarShiftItemView item = null;
            try {
                item = new CalendarShiftItemView();
                item.load();
            } catch (IOException e) {
                navController.openErrorDialog(e.getMessage(), true);
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, day);
            setGraphic(item.getRoot());
        }
    }
}
