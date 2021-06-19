package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
@Value
public class CalendarDay {

    ObjectProperty<LocalDate> dateProperty;
    @Accessors(fluent = false)
    ObservableList<ShiftDto> dateShiftList = FXCollections.observableArrayList();

    public CalendarDay(ObjectProperty<LocalDate> date, ObservableList<ShiftDto> shiftList) {
        this.dateProperty = date;
        date.addListener((observable, newValue, oldValue) -> this.updateDateShiftList(shiftList));
        shiftList.addListener((ListChangeListener<ShiftDto>) c -> updateDateShiftList(shiftList));
        updateDateShiftList(shiftList);
    }

    private void updateDateShiftList(ObservableList<ShiftDto> shiftList) {
        dateShiftList.clear();
        dateShiftList.addAll(shiftList.stream()
                .filter(shiftDto -> shiftDto.getDate().equals(dateProperty.get()))
                .collect(Collectors.toList())
        );
    }

}
