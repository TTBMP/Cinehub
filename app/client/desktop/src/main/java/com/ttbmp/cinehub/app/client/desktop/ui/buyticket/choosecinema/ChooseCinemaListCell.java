package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
public class ChooseCinemaListCell extends ListCell<CinemaDto> {
    private final Activity activity;
    private final NavController navController;
    public ChooseCinemaListCell(ListView<CinemaDto> cinemaDtoListView,Activity activity,NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    public void updateItem(CinemaDto request, boolean empty) {
        super.updateItem(request, empty);
        if (request != null) {
            ChooseCinemaItemView item = null;
            try {
                item = new ChooseCinemaItemView();
                item.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity,navController,request);
            setGraphic(item.getRoot());
        }
    }
}
