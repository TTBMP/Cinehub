package com.ttbmp.cinehub.ui.desktop.manageshift.detail;

import com.ttbmp.cinehub.app.dto.employee.UsherDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.ui.desktop.manageshift.modify.ModifyShiftView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */
public class ShowShiftDetailViewController extends ViewController {

    @FXML
    private Label errorLabel;

    @FXML
    private VBox errorVBox;

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
        errorLabel.textProperty().bind(viewModel.errorDaoProperty());
        if (viewModel.getEmployee(viewModel.getSelectedShift()) instanceof UsherDto) {
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
            activity.getUseCase(ManageEmployeesShiftUseCase.class).deleteShift(new ShiftRequest(
                    CinehubApplication.getSessionToken(),
                    viewModel.getSelectedShift().getId()));
            navController.navBack();
        });
        modifyShiftButton.setOnAction(a -> navController.navigate(new NavDestination(new ModifyShiftView())));
    }

}
