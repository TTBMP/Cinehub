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
        if (viewModel.selectedDayWeekProperty().get().getEmployee() instanceof UsherDto) {
            hallLabel.visibleProperty().bind(viewModel.hallVisibleProperty());
            hallComboBox.visibleProperty().bind(viewModel.hallVisibleProperty());
        } else {
            viewModel.getHallList().setAll(viewModel.selectedDayWeekProperty().get().getEmployee().getCinema().getHalList());
            hallComboBox.setItems(viewModel.getHallList());
            hallComboBox.valueProperty().bindBidirectional(viewModel.selectedHallProperty());
        }
        viewModel.repeatVisibleProperty().set(false);
        viewModel.errorAssignVisibleProperty().set(false);
        viewModel.selectedEndRepeatDayProperty().set(null);
        hallComboBox.setButtonCell(new HallFactory(null));
        hallComboBox.setCellFactory(HallFactory::new);
        hallComboBox.getSelectionModel().selectFirst();
        optionVBox.visibleProperty().bind(viewModel.repeatVisibleProperty());
        dateVBox.visibleProperty().bind(viewModel.repeatVisibleProperty());
        errorLabel.setVisible(false);
        viewModel.errorMessageProperty().addObserver(s -> errorLabel.setText(s));
        viewModel.errorMessageProperty().addObserver(s -> errorLabel.setVisible(!s.isEmpty()));
        viewModel.repeatVisibleProperty().set(viewModel.repeatVisibleProperty().get());
        shiftRepeatCheckBox.selectedProperty().bindBidirectional(viewModel.repeatVisibleProperty());
        viewModel.startSpinnerTimeProperty().set(LocalTime.NOON);
        viewModel.endSpinnerTimeProperty().set(LocalTime.NOON.plusHours(1));
        startSpinner.setValueFactory(new SpinnerStartValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));
        endSpinner.setValueFactory(new SpinnerEndValueFactory(viewModel.startSpinnerTimeProperty(), viewModel.endSpinnerTimeProperty()));
        repeatDatePicker.setDayCellFactory(date -> new RepeatDateCell(viewModel.selectedDayWeekProperty()));
        repeatDatePicker.valueProperty().bindBidirectional(viewModel.selectedEndRepeatDayProperty());
        optionRepeatComboBox.getItems().setAll(ShiftRepeatingOption.values());
        optionRepeatComboBox.setButtonCell(new ComboBoxOptionValueFactory(null));
        optionRepeatComboBox.setCellFactory(ComboBoxOptionValueFactory::new);
        optionRepeatComboBox.valueProperty().bindBidirectional(viewModel.selectedOptionProperty());
        optionRepeatComboBox.getSelectionModel().selectFirst();
        confirmButton.setOnAction(this::confirmButtonOnAction);
        cancelButton.setOnAction(a -> {
            if (shiftRepeatCheckBox.isSelected()) {
                viewModel.repeatVisibleProperty().set(!viewModel.repeatVisibleProperty().get());
            }
            viewModel.selectedOptionProperty().set(null);
            navController.goBack();
        });
    }

    private void confirmButtonOnAction(ActionEvent action) {
        var hallId = -1;
        if (viewModel.selectedHallProperty().get() != null) {
            hallId = viewModel.selectedHallProperty().get().getId();
        }
        if (!viewModel.repeatVisibleProperty().get()) {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).createShift(new CreateShiftRequest(
                    CinehubApplication.getSessionToken(),
                    viewModel.selectedDayWeekProperty().get().getEmployee().getId(),
                    viewModel.selectedDayWeekProperty().get().getDate(),
                    viewModel.startSpinnerTimeProperty().get().withNano(0),
                    viewModel.endSpinnerTimeProperty().get().withNano(0),
                    hallId)
            );
        } else {
            activity.getUseCase(ManageEmployeesShiftUseCase.class).createRepeatedShift(
                    new ShiftRepeatRequest(
                            CinehubApplication.getSessionToken(),
                            viewModel.selectedDayWeekProperty().get().getDate(),
                            viewModel.selectedEndRepeatDayProperty().get(),
                            viewModel.selectedOptionProperty().get().toString(),
                            viewModel.selectedDayWeekProperty().get().getEmployee().getId(),
                            viewModel.startSpinnerTimeProperty().get(),
                            viewModel.endSpinnerTimeProperty().get(),
                            hallId
                    )
            );
        }
        if (!viewModel.errorAssignVisibleProperty().get()) {
            viewModel.selectedOptionProperty().set(null);
            navController.goBack();
        }
    }
}
