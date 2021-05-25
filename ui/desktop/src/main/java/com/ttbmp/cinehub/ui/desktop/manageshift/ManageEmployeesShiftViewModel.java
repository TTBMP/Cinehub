package com.ttbmp.cinehub.ui.desktop.manageshift;


import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
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

    private final BooleanProperty errorAssignVisibility = new SimpleBooleanProperty();
    private final BooleanProperty errorModifyVisibility = new SimpleBooleanProperty();
    private final StringProperty error = new SimpleStringProperty();
    private final StringProperty errorDao = new SimpleStringProperty();

    private final BooleanProperty repeatVisibility = new SimpleBooleanProperty();
    private final ObjectProperty<LocalTime> startSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endSpinnerTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedEndRepeatDay = new SimpleObjectProperty<>();
    private final ObservableList<ShiftDto> shiftList = FXCollections.observableArrayList();
    private final ObjectProperty<ShiftDto> selectedShift = new SimpleObjectProperty<>();
    private final ObjectProperty<Day> selectedDayWeek = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedWeek = new SimpleObjectProperty<>();
    private final ObservableList<EmployeeShiftWeek> employeeShiftWeekList = FXCollections.observableArrayList();
    private final ObjectProperty<ShiftRepeatingOption> selectedOption = new SimpleObjectProperty<>(ShiftRepeatingOption.EVERY_DAY);
    private final ObservableList<String> options = FXCollections.observableArrayList();

    private final ObjectProperty<ShiftDto> shiftCreated = new SimpleObjectProperty<>();
    private final BooleanProperty hallVisibility = new SimpleBooleanProperty();

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
        selectedShiftEmployeeName.bind(ObjectBindings.map(selectedShift, shiftDto -> getEmployee(shiftDto).getName()));
        selectedShiftCinema.bind(ObjectBindings.map(selectedShift, shiftDto -> getEmployee(shiftDto).getCinema().getName()));
        selectedShiftEmployeeSurname.bind(ObjectBindings.map(selectedShift, shiftDto -> getEmployee(shiftDto).getSurname()));
        selectedShiftDate.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStart.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEnd.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEnd().toString()));
        selectedShiftRole.bind(ObjectBindings.map(selectedShift, shiftDto -> {
            if (getEmployee(shiftDto) instanceof ProjectionistDto) {
                return "Projectionist";
            }
            return "Usher";
        }));
        selectedShiftHall.bind(ObjectBindings.map(selectedShift, shiftDto -> {
            if (shiftDto instanceof ShiftProjectionistDto) {
                return ((ShiftProjectionistDto) shiftDto).getHallDto().getName();
            }
            return null;
        }));
    }

    public String getErrorDao() {
        return errorDao.get();
    }

    public void setErrorDao(String errorDao) {
        this.errorDao.set(errorDao);
    }

    public StringProperty errorDaoProperty() {
        return errorDao;
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

    public boolean isErrorAssignVisibility() {
        return errorAssignVisibility.get();
    }

    public void setErrorAssignVisibility(boolean errorAssignVisibility) {
        this.errorAssignVisibility.set(errorAssignVisibility);
    }

    public BooleanProperty errorAssignVisibilityProperty() {
        return errorAssignVisibility;
    }

    public boolean isErrorModifyVisibility() {
        return errorModifyVisibility.get();
    }

    public void setErrorModifyVisibility(boolean errorModifyVisibility) {
        this.errorModifyVisibility.set(errorModifyVisibility);
    }

    public BooleanProperty errorModifyVisibilityProperty() {
        return errorModifyVisibility;
    }

    public String getError() {
        return error.get();
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public StringProperty errorProperty() {
        return error;
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

    public Day getSelectedDayWeek() {
        return selectedDayWeek.get();
    }

    public void setSelectedDayWeek(Day selectedDay) {
        this.selectedDayWeek.set(selectedDay);
    }

    public ObjectProperty<Day> selectedDayWeekProperty() {
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

    public ShiftRepeatingOption getSelectedOption() {
        return selectedOption.get();
    }

    public void setSelectedOption(ShiftRepeatingOption selectedOption) {
        this.selectedOption.set(selectedOption);
    }

    public ObjectProperty<ShiftRepeatingOption> selectedOptionProperty() {
        return selectedOption;
    }

    public ObservableList<String> getOptions() {
        return options;
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

    public boolean isHallVisibility() {
        return hallVisibility.get();
    }

    public void setHallVisibility(boolean hallVisibility) {
        this.hallVisibility.set(hallVisibility);
    }

    public BooleanProperty hallVisibilityProperty() {
        return hallVisibility;
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

    public String getSelectedShiftCinema() {
        return selectedShiftCinema.get();
    }

    public void setSelectedShiftCinema(String selectedShiftCinema) {
        this.selectedShiftCinema.set(selectedShiftCinema);
    }

    public StringProperty selectedShiftCinemaProperty() {
        return selectedShiftCinema;
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

    public EmployeeDto getEmployee(ShiftDto shift) {
        return employeeShiftWeekList.stream()
                .map(EmployeeShiftWeek::getEmployeeDto)
                .filter(employeeDto -> employeeDto.getId().equals(shift.getEmployeeId()))
                .findAny()
                .orElse(null);
    }

}
