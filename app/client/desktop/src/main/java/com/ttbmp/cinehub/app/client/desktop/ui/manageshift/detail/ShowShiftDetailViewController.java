package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.detail;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.modify.ModifyShiftView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

public class ShowShiftDetailViewController extends ViewController {

    private ManageEmployeesShiftViewModel viewModel;

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
    private Label filmLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label hallLbl;

    @FXML
    private Button modifyShiftButton;

    @FXML
    private Button deleteShiftButton;

    @FXML
    private Button backButton;


    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        new ManageEmployeesShiftViewModel();
        if(viewModel.getSelectedShift().getEmployee().getRole().equals("maschera")){
            hallLabel.setVisible(false);
            hallLbl.setVisible(false);
        }
        else{
            cinemaLabel.textProperty().bind(viewModel.selectedShiftProperty().asString());
            hallLabel.textProperty().bind(viewModel.selectedShiftHallProperty());
        }




        backButton.setOnAction(a -> {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        nameLabel.textProperty().bind(viewModel.selectedShiftEmployeeNameProperty());
        surnameLabel.textProperty().bind(viewModel.selectedShiftEmployeeSurnameProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftRoleProperty());
        cinemaLabel.textProperty().bind(viewModel.selectedShiftCinemaProperty());

        if(viewModel.getSelectedShift().getDate().isBefore(LocalDate.now().plusDays(1))){
            modifyShiftButton.setVisible(false);
            deleteShiftButton.setVisible(false);
        }

        deleteShiftButton.setOnAction(a -> {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).deleteShift(new GetShiftRequest(ShiftDataMapper.mapToEntity(viewModel.getSelectedShift())));
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
                }
        );

    }






}
