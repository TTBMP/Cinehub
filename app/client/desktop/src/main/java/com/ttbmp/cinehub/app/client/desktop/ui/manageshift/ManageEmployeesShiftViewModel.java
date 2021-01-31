package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;


import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign.Option;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.DayWeek;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.app.client.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewModel;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.HallDto;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Massimo Mazzetti
 */


public class ManageEmployeesShiftViewModel implements ViewModel {

    private final ObjectProperty<CinemaDto> selectedCinema = new SimpleObjectProperty<>();
    private final ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();

    private final ObjectProperty<LocalDate> selectedDays = new SimpleObjectProperty<>();

    private final ObservableList<HallDto> hallList = FXCollections.observableArrayList();
    private final ObjectProperty<HallDto> selectedHall = new SimpleObjectProperty<>();

    private final BooleanProperty repeatVisibility = new SimpleBooleanProperty();
    private final ObjectProperty<LocalTime> startSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedEndRepeatDay = new SimpleObjectProperty<>();
    private final ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();
    private final ObjectProperty<ShiftDto> selectedShift = new SimpleObjectProperty<>();
    private final ObjectProperty<DayWeek> selectedDayWeek = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedWeek = new SimpleObjectProperty<>();
    private final ObservableList<EmployeeShiftWeek> employeeShiftWeekList = FXCollections.observableArrayList();
    private final ObjectProperty<Option> selectedOptions = new SimpleObjectProperty<>();
    private final ObservableList<String> options = FXCollections.observableArrayList();

    private final ObjectProperty<ShiftDto> shiftCreated = new SimpleObjectProperty<>();

    private final StringProperty selectedShiftEmployeeName = new SimpleStringProperty();
    private final StringProperty selectedShiftEmployeeSurname = new SimpleStringProperty();
    private final StringProperty selectedShiftDate = new SimpleStringProperty();
    private final StringProperty selectedShiftStart = new SimpleStringProperty();
    private final StringProperty selectedShiftEnd = new SimpleStringProperty();
    private final StringProperty selectedShiftRole = new SimpleStringProperty();
    private final StringProperty selectedShiftHall = new SimpleStringProperty();
    private final StringProperty selectedShiftCinema = new SimpleStringProperty();

    private final ObjectProperty<LocalTime> startSpinnerModifyTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endSpinnerModifyTime = new SimpleObjectProperty<>();

    public ManageEmployeesShiftViewModel() {
        selectedShiftEmployeeName.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEmployee().getName()));
        selectedShiftCinema.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEmployee().getCinema().getName()));
        selectedShiftEmployeeSurname.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEmployee().getSurname()));
        selectedShiftDate.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStart.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEnd.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEnd().toString()));
        selectedShiftRole.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEmployee().getRole()));
        selectedShiftHall.bind(ObjectBindings.map(selectedShift, shiftDto -> {
            if (shiftDto.getHall() != null)
                return shiftDto.getHall().getNum();
            else
                return null;
        }));
    }

    public ShiftDto getShiftCreated() {
        return shiftCreated.get();
    }

    public void setShiftCreated(ShiftDto shiftCreated) {
        this.shiftCreated.set(shiftCreated);
    }

    public ObjectProperty<ShiftDto> shiftCreatedProperty() {
        return shiftCreated;
    }

    public Option getSelectedOptions() {
        return selectedOptions.get();
    }

    public void setSelectedOptions(Option selectedOptions) {
        this.selectedOptions.set(selectedOptions);
    }

    public ObjectProperty<Option> selectedOptionsProperty() {
        return selectedOptions;
    }

    public ObservableList<String> getOptions() {
        return options;
    }

    public String getSelectedShiftCinema() {
        return selectedShiftCinema.get();
    }

    public void setSelectedShiftCinema(String selectedShiftCinema) {
        this.selectedShiftCinema.set(selectedShiftCinema);
    }

    public StringProperty selectedShiftCinemaProperty() {
        return selectedShiftCinema;
    }

    public CinemaDto getSelectedCinema() {
        return selectedCinema.get();
    }

    public void setSelectedCinema(CinemaDto selectedCinema) {
        this.selectedCinema.set(selectedCinema);
    }

    public ObjectProperty<CinemaDto> selectedCinemaProperty() {
        return selectedCinema;
    }

    public ObservableList<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public LocalDate getSelectedDays() {
        return selectedDays.get();
    }

    public void setSelectedDays(LocalDate selectedDays) {
        this.selectedDays.set(selectedDays);
    }

    public ObjectProperty<LocalDate> selectedDaysProperty() {
        return selectedDays;
    }

    public ObservableList<HallDto> getHallList() {
        return hallList;
    }

    public HallDto getSelectedHall() {
        return selectedHall.get();
    }

    public void setSelectedHall(HallDto selectedHall) {
        this.selectedHall.set(selectedHall);
    }

    public ObjectProperty<HallDto> selectedHallProperty() {
        return selectedHall;
    }

    public boolean isRepeatVisibility() {
        return repeatVisibility.get();
    }

    public void setRepeatVisibility(boolean repeatVisibility) {
        this.repeatVisibility.set(repeatVisibility);
    }

    public BooleanProperty repeatVisibilityProperty() {
        return repeatVisibility;
    }

    public LocalTime getStartSpinnerTime() {
        return startSpinnerTime.get();
    }

    public void setStartSpinnerTime(LocalTime startSpinnerTime) {
        this.startSpinnerTime.set(startSpinnerTime);
    }

    public ObjectProperty<LocalTime> startSpinnerTimeProperty() {
        return startSpinnerTime;
    }

    public LocalTime getEndSpinnerTime() {
        return endSpinnerTime.get();
    }

    public void setEndSpinnerTime(LocalTime endSpinnerTime) {
        this.endSpinnerTime.set(endSpinnerTime);
    }

    public ObjectProperty<LocalTime> endSpinnerTimeProperty() {
        return endSpinnerTime;
    }

    public LocalDate getSelectedEndRepeatDay() {
        return selectedEndRepeatDay.get();
    }

    public void setSelectedEndRepeatDay(LocalDate selectedEndRepeatDay) {
        this.selectedEndRepeatDay.set(selectedEndRepeatDay);
    }

    public ObjectProperty<LocalDate> selectedEndRepeatDayProperty() {
        return selectedEndRepeatDay;
    }

    public ObservableList<ShiftDto> getShiftList() {
        return shiftList;
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

    public DayWeek getSelectedDayWeek() {
        return selectedDayWeek.get();
    }

    public void setSelectedDayWeek(DayWeek selectedDayWeek) {
        this.selectedDayWeek.set(selectedDayWeek);
    }

    public ObjectProperty<DayWeek> selectedDayWeekProperty() {
        return selectedDayWeek;
    }

    public LocalDate getSelectedWeek() {
        return selectedWeek.get();
    }

    public void setSelectedWeek(LocalDate selectedWeek) {
        this.selectedWeek.set(selectedWeek);
    }

    public ObjectProperty<LocalDate> selectedWeekProperty() {
        return selectedWeek;
    }

    public ObservableList<EmployeeShiftWeek> getEmployeeShiftWeekList() {
        return employeeShiftWeekList;
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

    public String getSelectedShiftEmployeeSurname() {
        return selectedShiftEmployeeSurname.get();
    }

    public void setSelectedShiftEmployeeSurname(String selectedShiftEmployeeSurname) {
        this.selectedShiftEmployeeSurname.set(selectedShiftEmployeeSurname);
    }

    public StringProperty selectedShiftEmployeeSurnameProperty() {
        return selectedShiftEmployeeSurname;
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

    public String getSelectedShiftRole() {
        return selectedShiftRole.get();
    }

    public void setSelectedShiftRole(String selectedShiftRole) {
        this.selectedShiftRole.set(selectedShiftRole);
    }

    public StringProperty selectedShiftRoleProperty() {
        return selectedShiftRole;
    }

    public String getSelectedShiftHall() {
        return selectedShiftHall.get();
    }

    public void setSelectedShiftHall(String selectedShiftHall) {
        this.selectedShiftHall.set(selectedShiftHall);
    }

    public StringProperty selectedShiftHallProperty() {
        return selectedShiftHall;
    }

    public LocalTime getStartSpinnerModifyTime() {
        return startSpinnerModifyTime.get();
    }

    public void setStartSpinnerModifyTime(LocalTime startSpinnerModifyTime) {
        this.startSpinnerModifyTime.set(startSpinnerModifyTime);
    }

    public ObjectProperty<LocalTime> startSpinnerModifyTimeProperty() {
        return startSpinnerModifyTime;
    }

    public LocalTime getEndSpinnerModifyTime() {
        return endSpinnerModifyTime.get();
    }

    public void setEndSpinnerModifyTime(LocalTime endSpinnerModifyTime) {
        this.endSpinnerModifyTime.set(endSpinnerModifyTime);
    }

    public ObjectProperty<LocalTime> endSpinnerModifyTimeProperty() {
        return endSpinnerModifyTime;
    }
}
