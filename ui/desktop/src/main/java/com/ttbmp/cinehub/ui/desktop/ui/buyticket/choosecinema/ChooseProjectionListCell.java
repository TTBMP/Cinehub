package com.ttbmp.cinehub.ui.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.domain.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
@SuppressWarnings("java:S110")
public class ChooseProjectionListCell extends ListCell<ProjectionDto> {
    private final Activity activity;
    private final NavController navController;

    public ChooseProjectionListCell(Activity activity, NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    public void updateItem(ProjectionDto request, boolean empty) {
        super.updateItem(request, empty);
        if (request != null) {
            ChooseProjectionItemView item = null;
            try {
                item = new ChooseProjectionItemView();
                item.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, request);
            setGraphic(item.getRoot());
        } else {
            setGraphic(null);
        }
    }
}
