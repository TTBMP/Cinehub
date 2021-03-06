package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist.ProjectionistShiftDetailView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        var viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftEmployeeRoleProperty());
        cityLabel.textProperty().bind(viewModel.selectedShiftCinemaCityProperty());
        addressLabel.textProperty().bind(viewModel.selectedShiftCinemaAddressProperty());
        detailButton.setVisible(viewModel.projectionsDetailButtonVisibleProperty().getValue());
        detailButton.setOnAction(a -> navController.openView(ProjectionistShiftDetailView.class));
        requestChangeButton.setVisible(false);
        requestSwapButton.setVisible(false);
    }

}
