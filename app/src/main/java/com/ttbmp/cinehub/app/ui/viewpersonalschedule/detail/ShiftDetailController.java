package com.ttbmp.cinehub.app.ui.viewpersonalschedule.detail;

import com.ttbmp.cinehub.app.CinehubApplication;
import com.ttbmp.cinehub.app.di.ViewPersonalScheduleContainer;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fabio Buracchi
 */
public class ShiftDetailController {

    private final ViewPersonalScheduleContainer container = CinehubApplication.APP_CONTAINER.getContainer(
            ViewPersonalScheduleContainer.class
    );

    private final ViewPersonalScheduleViewModel viewModel = container.getViewModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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


    @FXML
    void initialize() {
        assertProperInjection();
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        locationLabel.setText("via Roma");
        changeButton.setVisible(false);
    }

    protected void assertProperInjection() {
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert startLabel != null : "fx:id=\"startLabel\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert endLabel != null : "fx:id=\"endLabel\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert requestSwapButton != null : "fx:id=\"requestSwapButton\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert requestChangeButton != null : "fx:id=\"requestChangeButton\" was not injected: check your FXML file 'shift_detail.fxml'.";
        assert changeButton != null : "fx:id=\"changeButton\" was not injected: check your FXML file 'shift_detail.fxml'.";
    }

}
