package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("java:S110")
public class ProjectionistListCell extends ListCell<ProjectionDto> {

    private final Activity activity;
    private final NavController navController;

    public ProjectionistListCell(Activity activity, NavController navController) {
        super();
        this.activity = activity;
        this.navController = navController;
    }

    @Override
    public void updateItem(ProjectionDto projection, boolean empty) {
        super.updateItem(projection, empty);
        if (projection != null) {
            ProjectionistShiftItemView item = null;
            try {
                item = new ProjectionistShiftItemView();
                item.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, projection);
            setGraphic(item.getRoot());
        } else {
            setVisible(false);
        }
    }

}
