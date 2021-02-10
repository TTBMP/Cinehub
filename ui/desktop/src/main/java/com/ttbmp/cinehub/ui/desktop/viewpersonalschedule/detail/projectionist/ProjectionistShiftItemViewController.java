package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftItemViewController extends ViewController {

    @FXML
    private Label timeLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView movieImageView;

    @FXML
    private Label durationMovieLabel;

    private ProjectionDto projectionDto;

    @Override
    protected void onLoad() {
        timeLabel.setText(projectionDto.getStartTime());
        titleLabel.setText(projectionDto.getMovieDto().getName());
        movieImageView.setImage(new Image(projectionDto.getMovieDto().getMovieUrl()));
        durationMovieLabel.setText(projectionDto.getMovieDto().getDuration());
    }

    public void load(Activity activity, NavController navController, ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
        load(activity, navController);
    }

}
