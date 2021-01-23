package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.DayWeek;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class RepeatDateCell extends DateCell {

    private ObjectProperty<DayWeek> selectedDate = new SimpleObjectProperty<>();

    public RepeatDateCell(DatePicker datePicker, ObjectProperty<DayWeek> selectedDate) {
        super();
        this.selectedDate.bind(selectedDate);
    }

    @Override
    public void updateItem(LocalDate date, boolean empty){
        super.updateItem(date, empty);
        if (date.isBefore(selectedDate.getValue().getDate().plusDays(1))) {
            setDisable(true);
        }

    }
}
