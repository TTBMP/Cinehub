package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
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
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */


public class ShowShiftViewController extends ViewController {
    private ManageEmployeesShiftViewModel viewModel;

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
    private TableColumn<EmployeeShiftWeek, DayWeek> mondayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> tuesdayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> wednesdayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> thursdayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> fridayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> saturdayTableColumn;

    @FXML
    private TableColumn<EmployeeShiftWeek, DayWeek> sundayTableColumn;


    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        activity.getUseCase(ManageEmployeesShiftUseCase.class).getCinemaList();
        cinemaComboBox.setItems(viewModel.getCinemaList());
        viewModel.selectedCinemaProperty().bind(cinemaComboBox.getSelectionModel().selectedItemProperty());

        shiftTableView.setSelectionModel(null);
        mondayTableColumn.setReorderable(false);


        periodDatePicker.valueProperty().bindBidirectional(viewModel.selectedWeekProperty());

        periodDatePicker.setValue(LocalDate.now());
        cinemaComboBox.setValue(cinemaComboBox.getItems().get(0));
        activity.getUseCase(ManageEmployeesShiftUseCase.class).getShiftList(new GetShiftListRequest(periodDatePicker.getValue(), CinemaDataMapper.matToEntity(cinemaComboBox.getValue())));

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
        populateShiftTable();
    }


    private void populateShiftTable() {
        shiftEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("employeeDto"));
        mondayTableColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));
        tuesdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        wednesdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        thursdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        fridayTableColumn.setCellValueFactory(new PropertyValueFactory<>("friday"));
        saturdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        sundayTableColumn.setCellValueFactory(new PropertyValueFactory<>("sunday"));
        shiftEmployeeTableColumn.setCellFactory(tableColumn -> new EmployeeCalendarTableCell(tableColumn, activity, navController));
        mondayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        tuesdayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        wednesdayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        thursdayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        fridayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        saturdayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        sundayTableColumn.setCellFactory(tableColumn -> new ShiftCalendarTableCell(tableColumn, activity, navController));
        shiftTableView.setItems(viewModel.getEmployeeShiftWeekList());
        viewModel.getEmployeeShiftWeekList().addListener((InvalidationListener) l -> shiftTableView.refresh());
    }

}
