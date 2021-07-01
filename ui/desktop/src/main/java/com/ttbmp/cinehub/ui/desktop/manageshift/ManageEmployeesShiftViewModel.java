package com.ttbmp.cinehub.ui.desktop.manageshift;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import com.ttbmp.cinehub.app.utilities.observer.Observable;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Massimo Mazzetti
 */
@Value
public class ManageEmployeesShiftViewModel implements ViewModel {

    ObjectProperty<LocalDate> selectedDayProperty = new SimpleObjectProperty<>();

    @Accessors(fluent = false)
    ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();
    ObjectProperty<CinemaDto> selectedCinemaProperty = new SimpleObjectProperty<>();

    @Accessors(fluent = false)
    ObservableList<HallDto> hallList = FXCollections.observableArrayList();
    ObjectProperty<HallDto> selectedHallProperty = new SimpleObjectProperty<>();

    BooleanProperty errorAssignVisibleProperty = new SimpleBooleanProperty();
    BooleanProperty errorModifyVisibleProperty = new SimpleBooleanProperty();
    Observable<String> errorMessageProperty = new Observable<>();
    StringProperty errorDaoProperty = new SimpleStringProperty();

    ObjectProperty<LocalTime> startSpinnerTimeProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalTime> endSpinnerTimeProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> selectedEndRepeatDayProperty = new SimpleObjectProperty<>();

    @Accessors(fluent = false)
    ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();
    ObjectProperty<ShiftDto> selectedShiftProperty = new SimpleObjectProperty<>();

    @Accessors(fluent = false)
    ObservableList<EmployeeShiftWeek> employeeShiftWeekList = FXCollections.observableArrayList();
    ObjectProperty<LocalDate> selectedWeekProperty = new SimpleObjectProperty<>();
    ObjectProperty<Day> selectedDayWeekProperty = new SimpleObjectProperty<>();

    BooleanProperty repeatVisibleProperty = new SimpleBooleanProperty();
    @Accessors(fluent = false)
    ObservableList<String> options = FXCollections.observableArrayList();
    ObjectProperty<ShiftRepeatingOption> selectedOptionProperty = new SimpleObjectProperty<>(ShiftRepeatingOption.EVERY_DAY);

    ObjectProperty<ShiftDto> shiftCreatedProperty = new SimpleObjectProperty<>();
    BooleanProperty hallVisibleProperty = new SimpleBooleanProperty();

    StringProperty selectedShiftEmployeeNameProperty = new SimpleStringProperty();
    StringProperty selectedShiftEmployeeSurnameProperty = new SimpleStringProperty();
    StringProperty selectedShiftDateProperty = new SimpleStringProperty();
    StringProperty selectedShiftStartProperty = new SimpleStringProperty();
    StringProperty selectedShiftEndProperty = new SimpleStringProperty();
    StringProperty selectedShiftRoleProperty = new SimpleStringProperty();
    StringProperty selectedShiftHallProperty = new SimpleStringProperty();
    StringProperty selectedShiftCinemaProperty = new SimpleStringProperty();

    ObjectProperty<LocalTime> startSpinnerModifyTimeProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalTime> endSpinnerModifyTimeProperty = new SimpleObjectProperty<>();

    public ManageEmployeesShiftViewModel() {
        selectedShiftCinemaProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> getEmployee(shiftDto).getCinema().getName()));
        selectedShiftEmployeeNameProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> getEmployee(shiftDto).getName()));
        selectedShiftEmployeeSurnameProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> getEmployee(shiftDto).getSurname()));
        selectedShiftDateProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStartProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEndProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getEnd().toString()));
        selectedShiftRoleProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> getEmployee(shiftDto).getRole().toString()));
        selectedShiftHallProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> {
            if (shiftDto.getType().equals(ShiftDto.ShiftType.PROJECTIONIST_SHIFT)) {
                return shiftDto.getHall().getName();
            }
            return null;
        }));
    }

    public EmployeeDto getEmployee(ShiftDto shift) {
        return employeeShiftWeekList.stream()
                .map(EmployeeShiftWeek::getEmployeeDto)
                .filter(employeeDto -> employeeDto.getId().equals(shift.getEmployeeId()))
                .findAny()
                .orElse(null);
    }

}
