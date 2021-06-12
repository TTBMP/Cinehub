package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * @author Ivan Palmieri
 */
public class ChooseProjectionItemViewController extends ViewController {

    private ProjectionDto projectionDto;


    @FXML
    private Label projectionTimeLabel;

    @FXML
    private Label hallNameLabel;

    @Override
    protected void onLoad() {
        Objects.requireNonNull(projectionDto);
        hallNameLabel.setText(String.valueOf(projectionDto.getHallDto().getName()));
        projectionTimeLabel.setText(projectionDto.getStartTime());

    }

    public void load(Activity activity, NavController navController, ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
        load(activity, navController);
    }

}
