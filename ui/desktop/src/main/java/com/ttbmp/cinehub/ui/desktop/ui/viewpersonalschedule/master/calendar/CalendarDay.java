package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar;

import com.ttbmp.cinehub.domain.dto.ShiftDto;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class CalendarDay {

    private final ObjectProperty<LocalDate> date;
    private final ObservableList<ShiftDto> dateShiftList = FXCollections.observableArrayList();

    public CalendarDay(ObjectProperty<LocalDate> date, ObservableList<ShiftDto> shiftList) {
        this.date = date;
        date.addListener((observable, newValue, oldValue) -> this.updateDateShiftList(shiftList));
        shiftList.addListener((ListChangeListener<ShiftDto>) c -> updateDateShiftList(shiftList));
        updateDateShiftList(shiftList);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObservableList<ShiftDto> getDateShiftList() {
        return dateShiftList;
    }

    private void updateDateShiftList(ObservableList<ShiftDto> shiftList) {
        dateShiftList.clear();
        dateShiftList.addAll(shiftList.stream()
                .filter(shiftDto -> shiftDto.getDate().equals(date.get()))
                .collect(Collectors.toList())
        );
    }
}
