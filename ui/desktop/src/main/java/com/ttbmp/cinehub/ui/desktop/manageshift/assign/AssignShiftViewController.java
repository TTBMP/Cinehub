package com.ttbmp.cinehub.ui.desktop.manageshift.assign;

import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.employee.UsherDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.CreateShiftRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRepeatRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.ComboBoxOptionValueFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.HallFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.SpinnerEndValueFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.SpinnerStartValueFactory;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalTime;

/**
 * @author Massimo Mazzetti
 */
public class AssignShiftViewController extends ViewController {

    private ManageEmployeesShiftViewModel viewModel;

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
    private ComboBox<ShiftRepeatingOption> optionRepeatComboBox;

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

    @FXML
    private Label errorLabel;

    @Override
    protected void onLoad() {

        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);


        if (viewModel.getSelectedDayWeek().getEmployee() instanceof UsherDto) {
            hallLabel.visibleProperty().bind(viewModel.hallVisibilityProperty());
            hallComboBox.visibleProperty().bind(viewModel.hallVisibilityProperty());
        } else {
            viewModel.getHallList().setAll(viewModel.getEmployee(viewModel.getSelectedShift()).getCinema().getHalList());
            hallComboBox.setItems(viewModel.getHallList());
            hallComboBox.valueProperty().bindBidirectional(viewModel.selectedHallProperty());

        }
        viewModel.setRepeatVisibility(false);
        viewModel.setErrorAssignVisibility(false);


        viewModel.setSelectedEndRepeatDay(null);
        hallComboBox.setButtonCell(new HallFactory(null));
        hallComboBox.setCellFactory(HallFactory::new);
        hallComboBox.getSelectionModel().selectFirst();


        errorVBox.visibleProperty().bind(viewModel.errorAssignVisibilityProperty());

        optionVBox.visibleProperty().bind(viewModel.repeatVisibilityProperty());
        dateVBox.visibleProperty().bind(viewModel.repeatVisibilityProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());

        viewModel.setRepeatVisibility(viewModel.isRepeatVisibility());

        shiftRepeatCheckBox.selectedProperty().bindBidirectional(viewModel.repeatVisibilityProperty());

        viewModel.setStartSpinnerTime(LocalTime.NOON);
        viewModel.setEndSpinnerTime(LocalTime.NOON.plusHours(1));

        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));

        repeatDatePicker.setDayCellFactory(date -> new RepeatDateCell(viewModel.selectedDayWeekProperty()));
        repeatDatePicker.valueProperty().bindBidirectional(viewModel.selectedEndRepeatDayProperty());

        optionRepeatComboBox.getItems().setAll(ShiftRepeatingOption.values());
        optionRepeatComboBox.setButtonCell(new ComboBoxOptionValueFactory(null));
        optionRepeatComboBox.setCellFactory(ComboBoxOptionValueFactory::new);
        optionRepeatComboBox.valueProperty().bindBidirectional(viewModel.selectedOptionProperty());
        confirmButton.setOnAction(this::confirmButtonOnAction);

        cancelButton.setOnAction(a -> {
            if (shiftRepeatCheckBox.isSelected()) {
                viewModel.setRepeatVisibility(!viewModel.isRepeatVisibility());
            }
            viewModel.setSelectedOption(null);
            navController.navBack();
        });
    }

    private void confirmButtonOnAction(ActionEvent action) {
        var hallId = -1;
        if (viewModel.getSelectedHall() != null) {
            hallId = viewModel.getSelectedHall().getId();
        }

        if (!viewModel.isRepeatVisibility()) {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).createShift(new CreateShiftRequest(
                    CinehubApplication.getSessionToken(),
                    viewModel.getSelectedDayWeek().getEmployee().getId(),
                    viewModel.getSelectedDayWeek().getDate(),
                    viewModel.getStartSpinnerTime().withNano(0),
                    viewModel.getEndSpinnerTime().withNano(0),
                    hallId)
            );
        } else {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).createRepeatedShift(
                    new ShiftRepeatRequest(
                            CinehubApplication.getSessionToken(),
                            viewModel.getSelectedDayWeek().getDate(),
                            viewModel.getSelectedEndRepeatDay(),
                            viewModel.getSelectedOption().toString(),
                            viewModel.getSelectedDayWeek().getEmployee().getId(),
                            viewModel.getStartSpinnerTime(),
                            viewModel.getEndSpinnerTime(),
                            hallId
                    )
            );
        }
        if (!viewModel.isErrorAssignVisibility()) {
            viewModel.setSelectedOption(null);
            navController.navBack();
        }
    }
}
