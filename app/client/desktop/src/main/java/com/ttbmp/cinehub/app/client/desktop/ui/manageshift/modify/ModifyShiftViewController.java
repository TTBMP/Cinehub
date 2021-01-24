package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.modify;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory.HallFactory;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory.SpinnerEndValueFactory;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory.SpinnerStartValueFactory;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.core.datamapper.HallDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.dto.HallDto;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetHallListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.ShiftRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private Button submitButton;

    @FXML
    private HBox errorHBox;

    @Override
    protected void onLoad() {

        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        errorHBox.setVisible(false);

        if (viewModel.getSelectedShift().getEmployee().getRole().equals("maschera")) {
            hallLabel.setVisible(false);
            hallComboBox.setVisible(false);
        } else {
            hallComboBox.getItems().clear();
            activity.getUseCase(ManageEmployeesShiftUseCase.class).getHallList(new GetHallListRequest(CinemaDataMapper.matToEntity(viewModel.getSelectedShift().getEmployee().getCinema())));
            hallComboBox.setItems(viewModel.getSalaList());
            viewModel.selectedSalaProperty().bind(hallComboBox.getSelectionModel().selectedItemProperty());
        }
        hallComboBox.setButtonCell(new HallFactory(null));
        hallComboBox.setCellFactory(HallFactory::new);

        nameLabel.textProperty().bind(viewModel.selectedShiftEmployeeNameProperty());
        surnameLabel.textProperty().bind(viewModel.selectedShiftEmployeeSurnameProperty());
        cinemaLabel.textProperty().bind(viewModel.selectedShiftCinemaProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftRoleProperty());

        dateDatePicker.setDayCellFactory(ModifyDateCell::new);
        dateDatePicker.setValue(LocalDate.parse(viewModel.getSelectedShiftDate()));

        viewModel.setStartSpinnerModifyTime(LocalTime.parse(viewModel.getSelectedShiftStart()));
        viewModel.setEndSpinnerModifyTime(LocalTime.parse(viewModel.getSelectedShiftEnd()));


        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerModifyTimeProperty(), viewModel.endSpinnerModifyTimeProperty()));
        submitButton.setOnAction(this::submitButtonOnAction);

        cancelButton.setOnAction(a -> {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void submitButtonOnAction(ActionEvent action) {

        if (viewModel.getStartSpinnerModifyTime() != null && viewModel.getEndSpinnerModifyTime() != null && dateDatePicker.getValue() != null) {
            Shift shift = createShift();

            if (shift != null && activity.getUseCase(ManageEmployeesShiftUseCase.class).saveShift(new ShiftRequest(shift))) {
                activity.getUseCase(ManageEmployeesShiftUseCase.class).deleteShift(new ShiftRequest(ShiftDataMapper.mapToEntity(viewModel.getSelectedShift())));

                viewModel.setSelectedShift(ShiftDataMapper.mapToDto(shift));
                try {
                    navController.popBackStack();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorHBox.setVisible(true);
            }

        } else {
            dateDatePicker.setPromptText("seleziona data");
            startSpinner.setPromptText("inserisci valore");
            endSpinner.setPromptText("inserisci valore");
        }
    }

    private Shift createShift() {
        EmployeeDto employee = viewModel.getSelectedShift().getEmployee();
        LocalDate date = dateDatePicker.getValue();
        LocalTime start = viewModel.getStartSpinnerModifyTime().withNano(0);
        LocalTime end = viewModel.getEndSpinnerModifyTime().withNano(0);

        Shift shift;
        if (employee.getRole().equals("maschera")) {
            shift = new Shift(EmployeeDataMapper.matToEntity(employee),
                    date.toString(),
                    start.toString(),
                    end.toString());
        } else {
            if (hallComboBox.getValue() != null) {
                shift = new Shift(EmployeeDataMapper.matToEntity(employee),
                        date.toString(),
                        start.toString(),
                        end.toString(),
                        HallDataMapper.matToEntity(hallComboBox.getValue()));
            } else {
                shift = null;
                hallComboBox.setStyle("-fx-background-color: red;");
                errorHBox.setVisible(true);
            }
        }
        return shift;
    }

}


