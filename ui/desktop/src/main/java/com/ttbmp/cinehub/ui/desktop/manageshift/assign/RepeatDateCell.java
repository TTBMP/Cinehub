package com.ttbmp.cinehub.ui.desktop.manageshift.assign;

import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DateCell;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class RepeatDateCell extends DateCell {

    private final ObjectProperty<Day> selectedDate = new SimpleObjectProperty<>();

    public RepeatDateCell(ObjectProperty<Day> selectedDate) {
        super();
        this.selectedDate.bind(selectedDate);
    }

    @Override
    public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        if (date.isBefore(selectedDate.getValue().getDate().plusDays(1))) {
            setDisable(true);
        }

    }
}
