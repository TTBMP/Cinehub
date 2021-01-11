package com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCell extends TableCell<Map<DayOfWeek, CalendarDay>, CalendarDay> {

    private CalendarTableCellView cell;

    @SuppressWarnings("unused")
    public CalendarTableCell(TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay> tableColumn) {
        super();
        try {
            cell = new CalendarTableCellView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prefHeightProperty().bind(tableColumn.widthProperty());
        Objects.requireNonNull(cell);
        setGraphic(cell.getRoot());
    }

    @Override
    protected void updateItem(CalendarDay calendarDay, boolean empty) {
        super.updateItem(calendarDay, empty);
        if (!empty) {
            Objects.requireNonNull(calendarDay);
            cell.getController().bind(calendarDay);
        } else {
            setVisible(false);
        }
    }

}
