package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;


import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Ivan Palmieri
 */
@SuppressWarnings("java:S110")
public class ChooseMovieListCell extends ListCell<MovieDto> {

    private final Activity activity;
    private final NavController navController;

    public ChooseMovieListCell(Activity activity, NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    public void updateItem(MovieDto request, boolean empty) {
        super.updateItem(request, empty);
        if (request != null) {
            ChooseMovieItemView item = null;
            try {
                item = new ChooseMovieItemView();
                item.load();
            } catch (IOException e) {
                navController.openErrorDialog(e.getMessage(), true);
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, request);
            setGraphic(item.getRoot());
        } else {
            setVisible(false);
        }
    }
}
