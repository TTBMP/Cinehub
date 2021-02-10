package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.detail;

import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.detail.projectionist.ProjectionistShiftDetailView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ShiftDetailViewController extends ViewController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Button requestSwapButton;

    @FXML
    private Button requestChangeButton;

    @FXML
    private Button detailButton;

    @Override
    protected void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftEmployeeRoleProperty());
        cityLabel.textProperty().bind(viewModel.selectedShiftCinemaCityProperty());
        addressLabel.textProperty().bind(viewModel.selectedShiftCinemaAddressProperty());
        detailButton.setVisible(viewModel.isIsProjectionsDetailButtonVisible());
        detailButton.setOnAction(a -> {
            try {
                navController.navigate(new NavDestination(new ProjectionistShiftDetailView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        requestChangeButton.setVisible(false);
        requestSwapButton.setVisible(false);
    }

}
