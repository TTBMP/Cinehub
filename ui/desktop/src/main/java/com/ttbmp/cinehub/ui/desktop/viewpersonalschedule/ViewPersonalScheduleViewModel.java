package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftProjectionistDto;
import com.ttbmp.cinehub.app.utilities.observer.Observable;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.CalendarDay;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.CalendarPage;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
@Value
public class ViewPersonalScheduleViewModel implements ViewModel {

    ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>(LocalDate.now());
    @Accessors(fluent = false)
    ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();

    ObjectProperty<CalendarPage> calendarPageProperty = new SimpleObjectProperty<>(new CalendarPage(dateProperty, shiftList));
    ObjectProperty<LocalDate> calendarPageFirstDateProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> calendarPageLastDateProperty = new SimpleObjectProperty<>();
    @Accessors(fluent = false)
    ObservableList<Map<DayOfWeek, CalendarDay>> shiftWeekList = calendarPageProperty.get().getShiftWeekList();

    ObjectProperty<EmployeeDto> currentEmployeeProperty = new SimpleObjectProperty<>();
    ObjectProperty<ShiftDto> selectedShiftProperty = new SimpleObjectProperty<>();
    StringProperty selectedShiftEmployeeNameProperty = new SimpleStringProperty();
    StringProperty selectedShiftEmployeeRoleProperty = new SimpleStringProperty();
    StringProperty selectedShiftCinemaCityProperty = new SimpleStringProperty();
    StringProperty selectedShiftCinemaAddressProperty = new SimpleStringProperty();
    StringProperty selectedShiftDateProperty = new SimpleStringProperty();
    StringProperty selectedShiftStartProperty = new SimpleStringProperty();
    StringProperty selectedShiftEndProperty = new SimpleStringProperty();

    StringProperty selectedProjectionistShiftHallProperty = new SimpleStringProperty();
    BooleanProperty projectionsDetailButtonVisibleProperty = new SimpleBooleanProperty();
    @Accessors(fluent = false)
    ObservableList<ProjectionDto> projectionList = FXCollections.observableArrayList();

    Observable<String> errorMessageProperty = new Observable<>();

    public ViewPersonalScheduleViewModel() {
        calendarPageFirstDateProperty.bind(calendarPageProperty().get().pageFirstDateProperty());
        calendarPageLastDateProperty.bind(calendarPageProperty().get().pageLastDateProperty());
        selectedShiftEmployeeNameProperty.bind(ObjectBindings.map(currentEmployeeProperty, EmployeeDto::getName));
        selectedShiftEmployeeRoleProperty.bind(ObjectBindings.map(currentEmployeeProperty, Object::toString));
        selectedShiftCinemaCityProperty.bind(ObjectBindings.map(currentEmployeeProperty, employeeDto -> employeeDto.getCinema().getCity()));
        selectedShiftCinemaAddressProperty.bind(ObjectBindings.map(currentEmployeeProperty, employeeDto -> employeeDto.getCinema().getAddress()));
        selectedShiftDateProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStartProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEndProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> shiftDto.getEnd().toString()));
        selectedProjectionistShiftHallProperty.bind(ObjectBindings.map(selectedShiftProperty, shiftDto -> ((ShiftProjectionistDto) shiftDto).getHallDto().getName()));
        projectionsDetailButtonVisibleProperty.bind(ObjectBindings.map(selectedShiftEmployeeRoleProperty, e -> e.equals("projectionist")));   // TODO: refactor
    }

}
