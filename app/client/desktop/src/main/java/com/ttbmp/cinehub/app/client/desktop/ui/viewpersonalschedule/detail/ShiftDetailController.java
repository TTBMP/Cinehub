package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.detail;

import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author Fabio Buracchi
 */
public class ShiftDetailController extends Controller {

    @FXML
    private Label dateLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Button requestSwapButton;

    @FXML
    private Button requestChangeButton;

    @FXML
    private Button changeButton;

    @Override
    public void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        locationLabel.setText("via Roma");
        changeButton.setVisible(false);
    }

}
