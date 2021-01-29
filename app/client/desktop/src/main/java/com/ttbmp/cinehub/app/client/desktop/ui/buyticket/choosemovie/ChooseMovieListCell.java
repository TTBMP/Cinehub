package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosemovie;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.core.dto.MovieDto;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
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
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, request);
            setGraphic(item.getRoot());
        } else {
            setVisible(false);
        }
    }
}
