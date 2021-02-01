package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.core.dto.ProjectionDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Objects;

public class ChooseProjectionItemViewController extends ViewController {

    private ProjectionDto projectionDto;


    @FXML
    private Label projectionTimeLabel;

    @Override
    protected void onLoad() {
        Objects.requireNonNull(projectionDto);

        projectionTimeLabel.setText(projectionDto.getStartTime());

    }

    public void load(Activity activity, NavController navController, ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
        load(activity, navController);
    }

}
