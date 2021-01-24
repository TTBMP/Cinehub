package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master.calendar.tablecell;

import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master.calendar.CalendarDay;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("java:S110")
public class CalendarTableCell extends TableCell<Map<DayOfWeek, CalendarDay>, CalendarDay> {

    private final Activity activity;
    private final NavController navController;
    private CalendarTableCellView cell;

    public CalendarTableCell(TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay> tableColumn, Activity activity, NavController navController) {
        super();
        prefHeightProperty().bind(tableColumn.widthProperty());
        try {
            cell = new CalendarTableCellView();
            cell.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(cell);
        this.activity = activity;
        this.navController = navController;
        setGraphic(cell.getRoot());
    }

    @Override
    protected void updateItem(CalendarDay calendarDay, boolean empty) {
        super.updateItem(calendarDay, empty);
        if (!empty) {
            Objects.requireNonNull(calendarDay);
            cell.getController().load(activity, navController, calendarDay);
        } else {
            setVisible(false);
        }
    }

}