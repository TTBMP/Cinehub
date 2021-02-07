package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.components;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */

public class ComboBoxCinemaValueFactory extends ListCell<CinemaDto> {

    public ComboBoxCinemaValueFactory(ListView<CinemaDto> listView) {

    }

    @Override
    protected void updateItem(CinemaDto item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getName());
        }
    }

}
