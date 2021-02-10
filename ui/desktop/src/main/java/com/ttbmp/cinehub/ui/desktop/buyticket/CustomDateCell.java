package com.ttbmp.cinehub.ui.desktop.buyticket;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@SuppressWarnings("java:S110")
public class CustomDateCell extends DateCell {

    @SuppressWarnings("unused")
    public CustomDateCell(DatePicker datePicker) {
        super();
    }

    @Override
    public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();
        setDisable(empty || date.compareTo(today) < 0);
    }


}
