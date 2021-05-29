package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

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
        try {
            if (projection != null) {
                var item = new ProjectionistShiftItemView();
                item.load();
                Objects.requireNonNull(item);
                item.getController().load(activity, navController, projection);
                setGraphic(item.getRoot());
            } else {
                setVisible(false);
            }
        } catch (IOException e) {
            navController.openErrorDialog(e.getMessage(), true);
        }
    }

}
