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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class ManageEmployeesShiftViewModel implements ViewModel {

    private final ObjectProperty<CinemaDto> selectedCinema = new SimpleObjectProperty<>();
    private final ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();

    private final ObjectProperty<LocalDate> selectedDays = new SimpleObjectProperty<>();

    private final ObservableList<HallDto> salaList = FXCollections.observableArrayList();
    private final ObjectProperty<HallDto> selectedSala = new SimpleObjectProperty<>();

    private final BooleanProperty repeatVisibility = new SimpleBooleanProperty();
    private final ObjectProperty<LocalTime> startSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedEndRepeatDay = new SimpleObjectProperty<>();
    private final ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();
    private final ObjectProperty<ShiftDto> selectedShift = new SimpleObjectProperty<>();
    private final ObjectProperty<DayWeek> selectedDayWeek = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedWeek = new SimpleObjectProperty<>();
    private final ObservableList<EmployeeShiftWeek> employeeShiftWeekList = FXCollections.observableArrayList();
    private final ObservableList<String> options = FXCollections.observableArrayList();

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

    public ObservableList<String> getOptions() {
        return options;
    }

    public String getSelectedShiftCinema() {
        return selectedShiftCinema.get();
    }

    public StringProperty selectedShiftCinemaProperty() {
        return selectedShiftCinema;
    }

    public void setSelectedShiftCinema(String selectedShiftCinema) {
        this.selectedShiftCinema.set(selectedShiftCinema);
    }

    public CinemaDto getSelectedCinema() {
        return selectedCinema.get();
    }

    public ObjectProperty<CinemaDto> selectedCinemaProperty() {
        return selectedCinema;
    }

    public void setSelectedCinema(CinemaDto selectedCinema) {
        this.selectedCinema.set(selectedCinema);
    }

    public ObservableList<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public LocalDate getSelectedDays() {
        return selectedDays.get();
    }

    public ObjectProperty<LocalDate> selectedDaysProperty() {
        return selectedDays;
    }

    public void setSelectedDays(LocalDate selectedDays) {
        this.selectedDays.set(selectedDays);
    }

    public ObservableList<HallDto> getSalaList() {
        return salaList;
    }

    public HallDto getSelectedSala() {
        return selectedSala.get();
    }

    public ObjectProperty<HallDto> selectedSalaProperty() {
        return selectedSala;
    }

    public void setSelectedSala(HallDto selectedSala) {
        this.selectedSala.set(selectedSala);
    }

    public boolean isRepeatVisibility() {
        return repeatVisibility.get();
    }

    public BooleanProperty repeatVisibilityProperty() {
        return repeatVisibility;
    }

    public void setRepeatVisibility(boolean repeatVisibility) {
        this.repeatVisibility.set(repeatVisibility);
    }

    public LocalTime getStartSpinnerTime() {
        return startSpinnerTime.get();
    }

    public ObjectProperty<LocalTime> startSpinnerTimeProperty() {
        return startSpinnerTime;
    }

    public void setStartSpinnerTime(LocalTime startSpinnerTime) {
        this.startSpinnerTime.set(startSpinnerTime);
    }

    public LocalTime getEndSpinnerTime() {
        return endSpinnerTime.get();
    }

    public ObjectProperty<LocalTime> endSpinnerTimeProperty() {
        return endSpinnerTime;
    }

    public void setEndSpinnerTime(LocalTime endSpinnerTime) {
        this.endSpinnerTime.set(endSpinnerTime);
    }

    public LocalDate getSelectedEndRepeatDay() {
        return selectedEndRepeatDay.get();
    }

    public ObjectProperty<LocalDate> selectedEndRepeatDayProperty() {
        return selectedEndRepeatDay;
    }

    public void setSelectedEndRepeatDay(LocalDate selectedEndRepeatDay) {
        this.selectedEndRepeatDay.set(selectedEndRepeatDay);
    }

    public ObservableList<ShiftDto> getShiftList() {
        return shiftList;
    }

    public ShiftDto getSelectedShift() {
        return selectedShift.get();
    }

    public ObjectProperty<ShiftDto> selectedShiftProperty() {
        return selectedShift;
    }

    public void setSelectedShift(ShiftDto selectedShift) {
        this.selectedShift.set(selectedShift);
    }

    public DayWeek getSelectedDayWeek() {
        return selectedDayWeek.get();
    }

    public ObjectProperty<DayWeek> selectedDayWeekProperty() {
        return selectedDayWeek;
    }

    public void setSelectedDayWeek(DayWeek selectedDayWeek) {
        this.selectedDayWeek.set(selectedDayWeek);
    }

    public LocalDate getSelectedWeek() {
        return selectedWeek.get();
    }

    public ObjectProperty<LocalDate> selectedWeekProperty() {
        return selectedWeek;
    }

    public void setSelectedWeek(LocalDate selectedWeek) {
        this.selectedWeek.set(selectedWeek);
    }

    public ObservableList<EmployeeShiftWeek> getEmployeeShiftWeekList() {
        return employeeShiftWeekList;
    }


    public String getSelectedShiftEmployeeName() {
        return selectedShiftEmployeeName.get();
    }

    public StringProperty selectedShiftEmployeeNameProperty() {
        return selectedShiftEmployeeName;
    }

    public void setSelectedShiftEmployeeName(String selectedShiftEmployeeName) {
        this.selectedShiftEmployeeName.set(selectedShiftEmployeeName);
    }

    public String getSelectedShiftEmployeeSurname() {
        return selectedShiftEmployeeSurname.get();
    }

    public StringProperty selectedShiftEmployeeSurnameProperty() {
        return selectedShiftEmployeeSurname;
    }

    public void setSelectedShiftEmployeeSurname(String selectedShiftEmployeeSurname) {
        this.selectedShiftEmployeeSurname.set(selectedShiftEmployeeSurname);
    }

    public String getSelectedShiftDate() {
        return selectedShiftDate.get();
    }

    public StringProperty selectedShiftDateProperty() {
        return selectedShiftDate;
    }

    public void setSelectedShiftDate(String selectedShiftDate) {
        this.selectedShiftDate.set(selectedShiftDate);
    }

    public String getSelectedShiftStart() {
        return selectedShiftStart.get();
    }

    public StringProperty selectedShiftStartProperty() {
        return selectedShiftStart;
    }

    public void setSelectedShiftStart(String selectedShiftStart) {
        this.selectedShiftStart.set(selectedShiftStart);
    }

    public String getSelectedShiftEnd() {
        return selectedShiftEnd.get();
    }

    public StringProperty selectedShiftEndProperty() {
        return selectedShiftEnd;
    }

    public void setSelectedShiftEnd(String selectedShiftEnd) {
        this.selectedShiftEnd.set(selectedShiftEnd);
    }

    public String getSelectedShiftRole() {
        return selectedShiftRole.get();
    }

    public StringProperty selectedShiftRoleProperty() {
        return selectedShiftRole;
    }

    public void setSelectedShiftRole(String selectedShiftRole) {
        this.selectedShiftRole.set(selectedShiftRole);
    }

    public String getSelectedShiftHall() {
        return selectedShiftHall.get();
    }

    public StringProperty selectedShiftHallProperty() {
        return selectedShiftHall;
    }

    public void setSelectedShiftHall(String selectedShiftHall) {
        this.selectedShiftHall.set(selectedShiftHall);
    }

    public LocalTime getStartSpinnerModifyTime() {
        return startSpinnerModifyTime.get();
    }

    public ObjectProperty<LocalTime> startSpinnerModifyTimeProperty() {
        return startSpinnerModifyTime;
    }

    public void setStartSpinnerModifyTime(LocalTime startSpinnerModifyTime) {
        this.startSpinnerModifyTime.set(startSpinnerModifyTime);
    }

    public LocalTime getEndSpinnerModifyTime() {
        return endSpinnerModifyTime.get();
    }

    public ObjectProperty<LocalTime> endSpinnerModifyTimeProperty() {
        return endSpinnerModifyTime;
    }

    public void setEndSpinnerModifyTime(LocalTime endSpinnerModifyTime) {
        this.endSpinnerModifyTime.set(endSpinnerModifyTime);
    }
}
