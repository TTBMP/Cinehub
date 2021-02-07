package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.modify;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class ModifyDateCell extends DateCell {

    public ModifyDateCell(DatePicker datePicker) {
    }

    @Override
    public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        if (date.isBefore(LocalDate.now().plusDays(1))) {
            setDisable(true);
        }

    }

}
