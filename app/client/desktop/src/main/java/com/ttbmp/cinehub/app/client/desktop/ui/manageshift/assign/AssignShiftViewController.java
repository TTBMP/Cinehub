package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory.ComboBoxOptionValueFactory;
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
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author Massimo Mazzetti
 */

public class AssignShiftViewController extends ViewController {

    private ManageEmployeesShiftViewModel viewModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<HallDto> hallComboBox;

    @FXML
    private HBox errorVBox;

    @FXML
    private Spinner<LocalTime> endSpinner;

    @FXML
    private Spinner<LocalTime> startSpinner;

    @FXML
    private CheckBox shiftRepeatCheckBox;

    @FXML
    private VBox optionVBox;

    @FXML
    private ComboBox<Option> optionRepeatComboBox;

    @FXML
    private VBox dateVBox;

    @FXML
    private DatePicker repeatDatePicker;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Label hallLabel;


    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);

        if (viewModel.getSelectedDayWeek().getEmployee().getRole().equals("maschera")) {
            hallLabel.setVisible(false);
            hallComboBox.setVisible(false);
        } else {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).getHallList(new GetHallListRequest(CinemaDataMapper.matToEntity(viewModel.getSelectedDayWeek().getEmployee().getCinema())));
            hallComboBox.setItems(viewModel.getSalaList());
            viewModel.selectedSalaProperty().bind(hallComboBox.getSelectionModel().selectedItemProperty());
        }


        hallComboBox.setButtonCell(new HallFactory(null));
        hallComboBox.setCellFactory(HallFactory::new);

        errorVBox.setVisible(false);
        optionVBox.visibleProperty().bind(viewModel.repeatVisibilityProperty());
        dateVBox.visibleProperty().bind(viewModel.repeatVisibilityProperty());
        shiftRepeatCheckBox.setOnAction(event -> viewModel.setRepeatVisibility(!viewModel.isRepeatVisibility()));

        viewModel.setStartSpinnerTime(LocalTime.NOON);
        viewModel.setEndSpinnerTime(LocalTime.NOON.plusHours(1));

        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));

        repeatDatePicker.setDayCellFactory(date -> new RepeatDateCell(viewModel.selectedDayWeekProperty()));
        repeatDatePicker.valueProperty().bindBidirectional(viewModel.selectedEndRepeatDayProperty());

        optionRepeatComboBox.getItems().setAll(Option.values());
        optionRepeatComboBox.setButtonCell(new ComboBoxOptionValueFactory(null));
        optionRepeatComboBox.setCellFactory(ComboBoxOptionValueFactory::new);
        confirmButton.setOnAction(this::confirmButtonOnAction);

        cancelButton.setOnAction(a -> {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmButtonOnAction(ActionEvent action) {
        if (viewModel.getStartSpinnerTime() != null && viewModel.getEndSpinnerTime() != null) {

            Shift shift = createShift();
            saveShift(shift);

        } else {
            startSpinner.setPromptText("inserisci valore");
            endSpinner.setPromptText("inserisci valore");
        }
    }

    private Shift createShift() {
        EmployeeDto employee = viewModel.getSelectedDayWeek().getEmployee();
        LocalTime start = viewModel.getStartSpinnerTime().withNano(0);
        LocalTime end = viewModel.getEndSpinnerTime().withNano(0);
        Shift shift = null;
        if (employee.getRole().equals("maschera")) {
            shift = new Shift(EmployeeDataMapper.matToEntity(employee),
                    viewModel.getSelectedDayWeek().getDate().toString(),
                    start.toString(),
                    end.toString());
        } else {
            if (hallComboBox.getValue() != null) {
                shift = new Shift(EmployeeDataMapper.matToEntity(employee),
                        viewModel.getSelectedDayWeek().getDate().toString(),
                        start.toString(),
                        end.toString(),
                        HallDataMapper.matToEntity(hallComboBox.getValue()));
            } else {
                hallComboBox.setStyle("-fx-background-color: red;");
                errorVBox.setVisible(true);
            }
        }
        return shift;
    }

    public void saveShift(Shift shift) {
        if (shift != null && activity.getUseCase(ManageEmployeesShiftUseCase.class).saveShift(new ShiftRequest(ShiftDataMapper.mapToEntity(ShiftDataMapper.mapToDto(shift))))) {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorVBox.setVisible(true);
        }
    }

}


