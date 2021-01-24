package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory;

import com.ttbmp.cinehub.core.dto.HallDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */

public class HallFactory extends ListCell<HallDto> {

    private ListView<HallDto> hallDto;

    public HallFactory(ListView<HallDto> hallDto) {
        this.hallDto = hallDto;
    }

    @Override
    protected void updateItem(HallDto item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getNum());
        }
    }

    public ListView<HallDto> getHallDto() {
        return hallDto;
    }

    public void setHallDto(ListView<HallDto> hallDto) {
        this.hallDto = hallDto;
    }
}