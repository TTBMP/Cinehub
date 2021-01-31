package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;

import javafx.scene.control.DateCell;


import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
public class CustomDateCell extends DateCell {


    @Override
    public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();
        setDisable(empty || date.compareTo(today) < 0);
    }


}
