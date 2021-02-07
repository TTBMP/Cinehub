package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.detail;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.modify.ModifyShiftView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.dto.UsherDto;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.ShiftRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
public class ShowShiftDetailViewController extends ViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label cinemaLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label hallLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label hallLabelText;

    @FXML
    private Button modifyShiftButton;

    @FXML
    private Button deleteShiftButton;

    @Override
    protected void onLoad() {
        ManageEmployeesShiftViewModel viewModel;
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        new ManageEmployeesShiftViewModel();
        if (viewModel.getSelectedShift().getEmployee() instanceof UsherDto) {
            hallLabel.visibleProperty().bind(viewModel.hallVisibilityProperty());
            hallLabelText.visibleProperty().bind(viewModel.hallVisibilityProperty());
        } else {
            hallLabel.textProperty().bind(viewModel.selectedShiftHallProperty());
        }
        nameLabel.textProperty().bind(viewModel.selectedShiftEmployeeNameProperty());
        surnameLabel.textProperty().bind(viewModel.selectedShiftEmployeeSurnameProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftRoleProperty());
        cinemaLabel.textProperty().bind(viewModel.selectedShiftCinemaProperty());

        if (viewModel.getSelectedShift().getDate().isBefore(LocalDate.now().plusDays(1))) {
            modifyShiftButton.setVisible(false);
            deleteShiftButton.setVisible(false);
        }

        deleteShiftButton.setOnAction(a -> {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).deleteShift(new ShiftRequest(viewModel.getSelectedShift()));
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        modifyShiftButton.setOnAction(a -> {
            try {
                navController.navigate(new NavDestination(new ModifyShiftView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
