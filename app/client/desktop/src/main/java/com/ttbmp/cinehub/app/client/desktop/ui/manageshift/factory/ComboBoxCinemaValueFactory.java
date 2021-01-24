package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */

public class ComboBoxCinemaValueFactory extends ListCell<CinemaDto> {
    private ListView<CinemaDto> cinemaDto;

    public ComboBoxCinemaValueFactory(ListView<CinemaDto> cinemaDto) {
        this.cinemaDto = cinemaDto;
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

    public ListView<CinemaDto> getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(ListView<CinemaDto> cinemaDto) {
        this.cinemaDto = cinemaDto;
    }
}
