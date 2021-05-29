package com.ttbmp.cinehub.ui.desktop.manageshift.modify;

import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.employee.UsherDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftModifyRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.HallFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.SpinnerEndValueFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.SpinnerStartValueFactory;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author Massimo Mazzetti
 */
public class ModifyShiftViewController extends ViewController {
    private ManageEmployeesShiftViewModel viewModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label cinemaLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label hallLabel;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Spinner<LocalTime> startSpinner;

    @FXML
    private Spinner<LocalTime> endSpinner;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<HallDto> hallComboBox;

    @FXML
    private Button confirmButton;

    @FXML
    private HBox errorHBox;

    @FXML
    private Label errorLabel;

    @Override
    protected void onLoad() {

        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);

        viewModel.setErrorAssignVisibility(false);
        errorHBox.visibleProperty().bind(viewModel.errorAssignVisibilityProperty());
        viewModel.errorProperty().addObserver(s -> errorLabel.setText(s));

        if (viewModel.getEmployee(viewModel.getSelectedShift()) instanceof UsherDto) {
            hallLabel.visibleProperty().bind(viewModel.hallVisibilityProperty());
            hallComboBox.visibleProperty().bind(viewModel.hallVisibilityProperty());
        } else {
            viewModel.getHallList().setAll(viewModel.getEmployee(viewModel.getSelectedShift()).getCinema().getHalList());
            hallComboBox.setItems(viewModel.getHallList());
            hallComboBox.valueProperty().bindBidirectional(viewModel.selectedHallProperty());

        }
        hallComboBox.setButtonCell(new HallFactory(null));
        hallComboBox.setCellFactory(HallFactory::new);
        hallComboBox.getSelectionModel().selectFirst();

        nameLabel.textProperty().bind(viewModel.selectedShiftEmployeeNameProperty());
        surnameLabel.textProperty().bind(viewModel.selectedShiftEmployeeSurnameProperty());
        cinemaLabel.textProperty().bind(viewModel.selectedShiftCinemaProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftRoleProperty());

        dateDatePicker.setDayCellFactory(ModifyDateCell::new);
        dateDatePicker.valueProperty().bindBidirectional(viewModel.selectedDaysProperty());


        viewModel.setStartSpinnerModifyTime(LocalTime.parse(viewModel.getSelectedShiftStart()));
        viewModel.setEndSpinnerModifyTime(LocalTime.parse(viewModel.getSelectedShiftEnd()));

        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        confirmButton.setOnAction(this::submitButtonOnAction);

        cancelButton.setOnAction(a -> navController.goBack());
    }

    private void submitButtonOnAction(ActionEvent action) {
        viewModel.setErrorAssignVisibility(false);
        var hallId = -1;
        if (viewModel.getSelectedHall() != null) {
            hallId = viewModel.getSelectedHall().getId();
        }
        activity.getUseCase(ManageEmployeesShiftUseCase.class).modifyShift(
                new ShiftModifyRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getSelectedShift().getEmployeeId(),
                        viewModel.getSelectedShift().getId(),
                        viewModel.getSelectedDays(),
                        viewModel.getStartSpinnerModifyTime().withNano(0),
                        viewModel.getEndSpinnerModifyTime().withNano(0),
                        hallId
                ));

        if (!viewModel.isErrorAssignVisibility()) {
            viewModel.setSelectedShift(viewModel.getShiftCreated());
            navController.goBack();
        }
    }

}


