package com.ttbmp.cinehub.ui.desktop.manageshift.modify;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
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
    private Label errorLabel;

    @Override
    protected void onLoad() {

        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);

        viewModel.errorAssignVisibleProperty().set(false);
        errorLabel.visibleProperty().bind(viewModel.errorAssignVisibleProperty());
        viewModel.errorMessageProperty().addObserver(s -> errorLabel.setText(s));

        if (viewModel.getEmployee(viewModel.selectedShiftProperty().get()).getRole().equals(EmployeeDto.EmployeeRole.USHER)) {
            hallLabel.visibleProperty().bind(viewModel.hallVisibleProperty());
            hallComboBox.visibleProperty().bind(viewModel.hallVisibleProperty());
        } else {
            viewModel.getHallList().setAll(viewModel.getEmployee(viewModel.selectedShiftProperty().get()).getCinema().getHalList());
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
        dateDatePicker.valueProperty().bindBidirectional(viewModel.selectedDayProperty());


        viewModel.startSpinnerModifyTimeProperty().set(LocalTime.parse(viewModel.selectedShiftStartProperty().get()));
        viewModel.endSpinnerModifyTimeProperty().set(LocalTime.parse(viewModel.selectedShiftEndProperty().get()));

        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        confirmButton.setOnAction(this::submitButtonOnAction);

        cancelButton.setOnAction(a -> navController.goBack());
    }

    private void submitButtonOnAction(ActionEvent action) {
        viewModel.errorAssignVisibleProperty().set(false);
        var hallId = -1;
        if (viewModel.selectedHallProperty().get() != null) {
            hallId = viewModel.selectedHallProperty().get().getId();
        }
        activity.getUseCase(ManageEmployeesShiftUseCase.class).modifyShift(
                new ShiftModifyRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.selectedShiftProperty().get().getId(),
                        viewModel.selectedDayProperty().get(),
                        viewModel.startSpinnerModifyTimeProperty().get().withNano(0),
                        viewModel.endSpinnerModifyTimeProperty().get().withNano(0),
                        hallId,
                        viewModel.selectedShiftProperty().get().getType().toString()
                ));

        if (!viewModel.errorAssignVisibleProperty().get()) {
            viewModel.selectedShiftProperty().set(viewModel.shiftCreatedProperty().get());
            navController.goBack();
        }
    }

}


