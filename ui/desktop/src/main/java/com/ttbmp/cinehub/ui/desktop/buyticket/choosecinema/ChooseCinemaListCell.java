package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Ivan Palmieri
 */
@SuppressWarnings("java:S110")
public class ChooseCinemaListCell extends ListCell<CinemaDto> {
    private final Activity activity;
    private final NavController navController;

    public ChooseCinemaListCell(Activity activity, NavController navController) {
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
            item.getController().load(activity, navController, request);
            setGraphic(item.getRoot());
        }
    }
}
