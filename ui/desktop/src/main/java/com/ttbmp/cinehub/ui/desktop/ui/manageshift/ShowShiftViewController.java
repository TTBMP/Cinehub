package com.ttbmp.cinehub.ui.desktop.ui.manageshift;

import com.ttbmp.cinehub.domain.dto.CinemaDto;
import com.ttbmp.cinehub.domain.dto.EmployeeDto;
import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.domain.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.ui.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.components.ComboBoxCinemaValueFactory;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.table.DayWeek;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.table.EmployeeCalendarTableCell;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.table.ShiftCalendarTableCell;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
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

        shiftTableView.setSelectionModel(null);

        cinemaComboBox.setButtonCell(new ComboBoxCinemaValueFactory(null));
        cinemaComboBox.setCellFactory(ComboBoxCinemaValueFactory::new);
        cinemaComboBox.valueProperty().bindBidirectional(viewModel.selectedCinemaProperty());
        cinemaComboBox.setValue(cinemaComboBox.getItems().get(0));

        periodDatePicker.valueProperty().bindBidirectional(viewModel.selectedWeekProperty());

        periodDatePicker.setValue(LocalDate.now());


        activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                new GetShiftListRequest(
                        viewModel.getSelectedWeek(),
                        viewModel.getSelectedCinema()
                )
        );

        previousButton.setOnAction(a -> periodDatePicker.setValue(viewModel.getSelectedWeek().minusWeeks(1)));
        nextButton.setOnAction(a -> periodDatePicker.setValue(viewModel.getSelectedWeek().plusWeeks(1)));
        todayButton.setOnAction(a -> periodDatePicker.setValue(LocalDate.now()));

        periodDatePicker.setOnAction(a -> activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                new GetShiftListRequest(
                        viewModel.getSelectedWeek(),
                        viewModel.getSelectedCinema()
                )
        ));
        cinemaComboBox.setOnAction(a -> activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                new GetShiftListRequest(
                        viewModel.getSelectedWeek(),
                        viewModel.getSelectedCinema()
                )
        ));
        shiftEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("employeeDto"));
        shiftEmployeeTableColumn.setCellFactory(tableColumn -> new EmployeeCalendarTableCell(activity, navController));
        shiftEmployeeTableColumn.setSortable(false);
        shiftEmployeeTableColumn.setReorderable(false);
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