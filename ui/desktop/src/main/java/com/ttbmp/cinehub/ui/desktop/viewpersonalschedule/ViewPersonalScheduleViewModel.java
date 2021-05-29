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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleViewModel implements ViewModel {

    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(LocalDate.now());
    private final ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();

    private final ObjectProperty<CalendarPage> calendarPage = new SimpleObjectProperty<>(new CalendarPage(date, shiftList));
    private final ObjectProperty<LocalDate> calendarPageFirstDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> calendarPageLastDate = new SimpleObjectProperty<>();
    private final ObservableList<Map<DayOfWeek, CalendarDay>> shiftWeekList = calendarPage.get().getShiftWeekList();

    private final ObjectProperty<EmployeeDto> currentEmployee = new SimpleObjectProperty<>();
    private final ObjectProperty<ShiftDto> selectedShift = new SimpleObjectProperty<>();
    private final StringProperty selectedShiftEmployeeName = new SimpleStringProperty();
    private final StringProperty selectedShiftEmployeeRole = new SimpleStringProperty();
    private final StringProperty selectedShiftCinemaCity = new SimpleStringProperty();
    private final StringProperty selectedShiftCinemaAddress = new SimpleStringProperty();
    private final StringProperty selectedShiftDate = new SimpleStringProperty();
    private final StringProperty selectedShiftStart = new SimpleStringProperty();
    private final StringProperty selectedShiftEnd = new SimpleStringProperty();

    private final StringProperty selectedProjectionistShiftHall = new SimpleStringProperty();
    private final BooleanProperty isProjectionsDetailButtonVisible = new SimpleBooleanProperty();
    private final ObservableList<ProjectionDto> projectionList = FXCollections.observableArrayList();

    private final Observable<String> errorMessage = new Observable<>();

    public ViewPersonalScheduleViewModel() {
        calendarPageFirstDate.bind(getCalendarPage().pageFirstDateProperty());
        calendarPageLastDate.bind(getCalendarPage().pageLastDateProperty());
        selectedShiftEmployeeName.bind(ObjectBindings.map(currentEmployee, EmployeeDto::getName));
        selectedShiftEmployeeRole.bind(ObjectBindings.map(currentEmployee, Object::toString));
        selectedShiftCinemaCity.bind(ObjectBindings.map(currentEmployee, employeeDto -> employeeDto.getCinema().getCity()));
        selectedShiftCinemaAddress.bind(ObjectBindings.map(currentEmployee, employeeDto -> employeeDto.getCinema().getAddress()));
        selectedShiftDate.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStart.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEnd.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEnd().toString()));
        selectedProjectionistShiftHall.bind(ObjectBindings.map(selectedShift, shiftDto -> ((ShiftProjectionistDto) shiftDto).getHallDto().getId().toString()));
        isProjectionsDetailButtonVisible.bind(ObjectBindings.map(selectedShiftEmployeeRole, e -> e.equals("projectionist")));   // TODO: refactor
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObservableList<ShiftDto> getShiftList() {
        return shiftList;
    }

    public CalendarPage getCalendarPage() {
        return calendarPage.get();
    }

    public void setCalendarPage(CalendarPage calendarPage) {
        this.calendarPage.set(calendarPage);
    }

    public ObjectProperty<CalendarPage> calendarPageProperty() {
        return calendarPage;
    }

    public LocalDate getCalendarPageFirstDate() {
        return calendarPageFirstDate.get();
    }

    public void setCalendarPageFirstDate(LocalDate calendarPageFirstDate) {
        this.calendarPageFirstDate.set(calendarPageFirstDate);
    }

    public ObjectProperty<LocalDate> calendarPageFirstDateProperty() {
        return calendarPageFirstDate;
    }

    public LocalDate getCalendarPageLastDate() {
        return calendarPageLastDate.get();
    }

    public void setCalendarPageLastDate(LocalDate calendarPageLastDate) {
        this.calendarPageLastDate.set(calendarPageLastDate);
    }

    public ObjectProperty<LocalDate> calendarPageLastDateProperty() {
        return calendarPageLastDate;
    }

    public ObservableList<Map<DayOfWeek, CalendarDay>> getShiftWeekList() {
        return shiftWeekList;
    }

    public ShiftDto getSelectedShift() {
        return selectedShift.get();
    }

    public void setSelectedShift(ShiftDto selectedShift) {
        this.selectedShift.set(selectedShift);
    }

    public ObjectProperty<ShiftDto> selectedShiftProperty() {
        return selectedShift;
    }

    public String getSelectedShiftEmployeeName() {
        return selectedShiftEmployeeName.get();
    }

    public void setSelectedShiftEmployeeName(String selectedShiftEmployeeName) {
        this.selectedShiftEmployeeName.set(selectedShiftEmployeeName);
    }

    public StringProperty selectedShiftEmployeeNameProperty() {
        return selectedShiftEmployeeName;
    }

    public String getSelectedShiftEmployeeRole() {
        return selectedShiftEmployeeRole.get();
    }

    public void setSelectedShiftEmployeeRole(String selectedShiftEmployeeRole) {
        this.selectedShiftEmployeeRole.set(selectedShiftEmployeeRole);
    }

    public StringProperty selectedShiftEmployeeRoleProperty() {
        return selectedShiftEmployeeRole;
    }

    public String getSelectedShiftCinemaCity() {
        return selectedShiftCinemaCity.get();
    }

    public void setSelectedShiftCinemaCity(String selectedShiftCinemaCity) {
        this.selectedShiftCinemaCity.set(selectedShiftCinemaCity);
    }

    public StringProperty selectedShiftCinemaCityProperty() {
        return selectedShiftCinemaCity;
    }

    public String getSelectedShiftCinemaAddress() {
        return selectedShiftCinemaAddress.get();
    }

    public void setSelectedShiftCinemaAddress(String selectedShiftCinemaAddress) {
        this.selectedShiftCinemaAddress.set(selectedShiftCinemaAddress);
    }

    public StringProperty selectedShiftCinemaAddressProperty() {
        return selectedShiftCinemaAddress;
    }

    public String getSelectedShiftDate() {
        return selectedShiftDate.get();
    }

    public void setSelectedShiftDate(String selectedShiftDate) {
        this.selectedShiftDate.set(selectedShiftDate);
    }

    public StringProperty selectedShiftDateProperty() {
        return selectedShiftDate;
    }

    public String getSelectedShiftStart() {
        return selectedShiftStart.get();
    }

    public void setSelectedShiftStart(String selectedShiftStart) {
        this.selectedShiftStart.set(selectedShiftStart);
    }

    public StringProperty selectedShiftStartProperty() {
        return selectedShiftStart;
    }

    public String getSelectedShiftEnd() {
        return selectedShiftEnd.get();
    }

    public void setSelectedShiftEnd(String selectedShiftEnd) {
        this.selectedShiftEnd.set(selectedShiftEnd);
    }

    public StringProperty selectedShiftEndProperty() {
        return selectedShiftEnd;
    }

    public String getSelectedProjectionistShiftHall() {
        return selectedProjectionistShiftHall.get();
    }

    public void setSelectedProjectionistShiftHall(String selectedProjectionistShiftHall) {
        this.selectedProjectionistShiftHall.set(selectedProjectionistShiftHall);
    }

    public StringProperty selectedProjectionistShiftHallProperty() {
        return selectedProjectionistShiftHall;
    }

    public boolean isIsProjectionsDetailButtonVisible() {
        return isProjectionsDetailButtonVisible.get();
    }

    public void setIsProjectionsDetailButtonVisible(boolean isProjectionsDetailButtonVisible) {
        this.isProjectionsDetailButtonVisible.set(isProjectionsDetailButtonVisible);
    }

    public BooleanProperty isProjectionsDetailButtonVisibleProperty() {
        return isProjectionsDetailButtonVisible;
    }

    public ObservableList<ProjectionDto> getProjectionList() {
        return projectionList;
    }

    public EmployeeDto getCurrentEmployee() {
        return currentEmployee.get();
    }

    public void setCurrentEmployee(EmployeeDto currentEmployee) {
        this.currentEmployee.set(currentEmployee);
    }

    public ObjectProperty<EmployeeDto> currentEmployeeProperty() {
        return currentEmployee;
    }

    public Observable<String> errorMessageProperty() {
        return errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage.getValue();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setValue(errorMessage);
    }

}
