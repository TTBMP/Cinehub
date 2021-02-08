package com.ttbmp.cinehub.ui.desktop.ui.manageshift.components;

import com.ttbmp.cinehub.domain.dto.HallDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class HallFactory extends ListCell<HallDto> {


    public HallFactory(ListView<HallDto> listView) {

    }

    @Override
    protected void updateItem(HallDto item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getId().toString());
        }
    }

}