package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.error;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ErrorDialogViewController extends ViewController {

    @FXML
    private Label messageLabel;

    @FXML
    private Button closeButton;

    @Override
    protected void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        messageLabel.setText(viewModel.getErrorMessage());
        closeButton.setOnAction(e -> {
            try {
                navController.navBack();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

}
