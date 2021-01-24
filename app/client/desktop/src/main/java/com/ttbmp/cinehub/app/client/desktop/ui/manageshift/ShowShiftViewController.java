package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory.ComboBoxCinemaValueFactory;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.DayWeek;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.EmployeeCalendarTableCell;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.ShiftCalendarTableCell;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftListRequest;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */


public class ShowShiftViewController extends ViewController {
    ManageEmployeesShiftViewModel viewModel;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;


    @FXML
    private ComboBox<CinemaDto> cinemaComboBox;

    @FXML
    private DatePicker periodDatePicker;

    @FXML
    private TableView<EmployeeShiftWeek> shiftTableView;

    @FXML
    private TableColumn<EmployeeShiftWeek, EmployeeDto> shiftEmployeeTableColumn;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button todayButton;

    @SuppressWarnings("unchecked")
    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        activity.getUseCase(ManageEmployeesShiftUseCase.class).getCinemaList();
        cinemaComboBox.setItems(viewModel.getCinemaList());
        viewModel.selectedCinemaProperty().bind(cinemaComboBox.getSelectionModel().selectedItemProperty());

        shiftTableView.setSelectionModel(null);

        cinemaComboBox.setButtonCell(new ComboBoxCinemaValueFactory(null));
        cinemaComboBox.setCellFactory(ComboBoxCinemaValueFactory::new);

        periodDatePicker.valueProperty().bindBidirectional(viewModel.selectedWeekProperty());

        periodDatePicker.setValue(LocalDate.now());
        cinemaComboBox.setValue(cinemaComboBox.getItems().get(0));
        activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(new GetShiftListRequest(periodDatePicker.getValue(), CinemaDataMapper.matToEntity(cinemaComboBox.getValue())));

        previousButton.setOnAction(a -> periodDatePicker.setValue(periodDatePicker.getValue().minusWeeks(1)));
        nextButton.setOnAction(a -> periodDatePicker.setValue(periodDatePicker.getValue().plusWeeks(1)));
        todayButton.setOnAction(a -> periodDatePicker.setValue(LocalDate.now()));

        periodDatePicker.setOnAction(a -> {
            if (cinemaComboBox.getValue() != null) {
                activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(new GetShiftListRequest(periodDatePicker.getValue(), CinemaDataMapper.matToEntity(cinemaComboBox.getValue())));
            }
        });
        cinemaComboBox.setOnAction(a -> {
            if (periodDatePicker.getValue() != null) {
                activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(new GetShiftListRequest(periodDatePicker.getValue(), CinemaDataMapper.matToEntity(cinemaComboBox.getValue())));
            }
        });
        shiftEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("employeeDto"));
        shiftEmployeeTableColumn.setCellFactory(tableColumn -> new EmployeeCalendarTableCell(activity, navController));
        shiftEmployeeTableColumn.setSortable(false);
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            TableColumn<EmployeeShiftWeek, DayWeek> column = (TableColumn<EmployeeShiftWeek, DayWeek>) shiftTableView.getColumns().get(dayOfWeek.getValue());
            column.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDayOfWeek(dayOfWeek)));
            column.setCellFactory(tableColumn -> new ShiftCalendarTableCell(activity, navController));
            column.setReorderable(false);
            column.setSortable(false);
        }
        shiftTableView.setItems(viewModel.getEmployeeShiftWeekList());
        viewModel.getEmployeeShiftWeekList().addListener((InvalidationListener) l -> shiftTableView.refresh());
    }

}
