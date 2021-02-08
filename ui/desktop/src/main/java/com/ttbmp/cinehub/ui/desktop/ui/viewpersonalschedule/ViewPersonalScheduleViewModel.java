package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.domain.dto.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar.CalendarDay;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar.CalendarPage;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private final ObjectProperty<ShiftDto> selectedShift = new SimpleObjectProperty<>();
    private final StringProperty selectedShiftEmployeeName = new SimpleStringProperty();
    private final StringProperty selectedShiftDate = new SimpleStringProperty();
    private final StringProperty selectedShiftStart = new SimpleStringProperty();
    private final StringProperty selectedShiftEnd = new SimpleStringProperty();

    public ViewPersonalScheduleViewModel() {
        calendarPageFirstDate.bind(getCalendarPage().pageFirstDateProperty());
        calendarPageLastDate.bind(getCalendarPage().pageLastDateProperty());
        selectedShiftEmployeeName.bind(ObjectBindings.map(selectedShift, ShiftDto::getEmployeeName));
        selectedShiftDate.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getDate().toString()));
        selectedShiftStart.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getStart().toString()));
        selectedShiftEnd.bind(ObjectBindings.map(selectedShift, shiftDto -> shiftDto.getEnd().toString()));
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

}
