package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetCinemaListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.manageshift.components.ComboBoxCinemaValueFactory;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeCalendarTableCell;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.ShiftCalendarTableCell;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

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
    private Label errorLabel;

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

        errorLabel.textProperty().bind(viewModel.errorDaoProperty());

        activity.getUseCase(ManageEmployeesShiftUseCase.class).getCinemaList(new GetCinemaListRequest(CinehubApplication.getSessionToken()));
        cinemaComboBox.setItems(viewModel.getCinemaList());

        shiftTableView.setSelectionModel(null);

        cinemaComboBox.setButtonCell(new ComboBoxCinemaValueFactory(null));
        cinemaComboBox.setCellFactory(ComboBoxCinemaValueFactory::new);
        cinemaComboBox.valueProperty().bindBidirectional(viewModel.selectedCinemaProperty());

        cinemaComboBox.getSelectionModel().selectFirst();

        periodDatePicker.valueProperty().bindBidirectional(viewModel.selectedWeekProperty());

        periodDatePicker.setValue(LocalDate.now());

        activity.getUseCase((ManageEmployeesShiftUseCase.class)).getEmployeeList(
                new GetEmployeeListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getSelectedCinema().getId()
                ));

        activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                new GetShiftListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getSelectedCinema().getId(),
                        viewModel.getSelectedWeek().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                        viewModel.getSelectedWeek().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                )
        );

        previousButton.setOnAction(a -> periodDatePicker.setValue(viewModel.getSelectedWeek().minusWeeks(1)));
        nextButton.setOnAction(a -> periodDatePicker.setValue(viewModel.getSelectedWeek().plusWeeks(1)));
        todayButton.setOnAction(a -> periodDatePicker.setValue(LocalDate.now()));


        periodDatePicker.setOnAction(a -> activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                new GetShiftListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getSelectedCinema().getId(),
                        viewModel.getSelectedWeek().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                        viewModel.getSelectedWeek().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                )
        ));
        cinemaComboBox.setOnAction(a -> {
            activity.getUseCase((ManageEmployeesShiftUseCase.class)).getEmployeeList(
                    new GetEmployeeListRequest(
                            CinehubApplication.getSessionToken(),
                            viewModel.getSelectedCinema().getId()
                    ));

            activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(
                    new GetShiftListRequest(
                            CinehubApplication.getSessionToken(),
                            viewModel.getSelectedCinema().getId(),
                            viewModel.getSelectedWeek().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                            viewModel.getSelectedWeek().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                    ));

        });
        shiftEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("employeeDto"));
        shiftEmployeeTableColumn.setCellFactory(tableColumn -> new EmployeeCalendarTableCell(activity, navController));
        shiftEmployeeTableColumn.setSortable(false);
        shiftEmployeeTableColumn.setReorderable(false);
        for (var dayOfWeek : DayOfWeek.values()) {
            var column = (TableColumn<EmployeeShiftWeek, Day>) shiftTableView.getColumns().get(dayOfWeek.getValue());
            column.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDayOfWeek(dayOfWeek)));
            column.setCellFactory(tableColumn -> new ShiftCalendarTableCell(activity, navController));
            column.setReorderable(false);
            column.setSortable(false);
        }
        shiftTableView.setItems(viewModel.getEmployeeShiftWeekList());
        viewModel.getEmployeeShiftWeekList().addListener((InvalidationListener) l -> shiftTableView.refresh());
        viewModel.errorProperty().addObserver(s -> navController.openErrorDialog(s, false));

    }
}