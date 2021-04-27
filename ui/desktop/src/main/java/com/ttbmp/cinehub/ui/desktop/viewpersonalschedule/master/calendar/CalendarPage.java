package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;

/**
 * @author Fabio Buracchi
 */
public class CalendarPage {

    private final ObservableList<Map<DayOfWeek, CalendarDay>> shiftWeekList = FXCollections.observableArrayList();
    private final ObjectProperty<LocalDate> pageFirstDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> pageLastDate = new SimpleObjectProperty<>();

    public CalendarPage(ObjectProperty<LocalDate> date, ObservableList<ShiftDto> shiftList) {
        List<ObjectProperty<LocalDate>> dateList = IntStream.range(0, 42)
                .mapToObj(i -> new SimpleObjectProperty<LocalDate>())
                .collect(Collectors.toList());
        dateList.get(0).bind(ObjectBindings.map(date, localDate -> localDate.with(TemporalAdjusters.firstDayOfMonth())
                .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()))));
        for (var i = 1; i < 42; i++) {
            dateList.get(i).bind(ObjectBindings.map(dateList.get(i - 1), localDate -> localDate.plusDays(1)));
        }
        Iterator<ObjectProperty<LocalDate>> dateIterator = dateList.listIterator();
        for (var i = 0; i < 6; i++) {
            shiftWeekList.add(new EnumMap<>(DayOfWeek.class));
            for (var dayOfWeek : DayOfWeek.values()) {
                shiftWeekList.get(i).put(dayOfWeek, new CalendarDay(dateIterator.next(), shiftList));
            }
        }
        pageFirstDate.bind(ObjectBindings.map(dateList.get(0), identity()));
        pageLastDate.bind(ObjectBindings.map(dateList.get(41), identity()));
    }

    public ObservableList<Map<DayOfWeek, CalendarDay>> getShiftWeekList() {
        return shiftWeekList;
    }

    public LocalDate getPageFirstDate() {
        return pageFirstDate.get();
    }

    public ObjectProperty<LocalDate> pageFirstDateProperty() {
        return pageFirstDate;
    }

    public LocalDate getPageLastDate() {
        return pageLastDate.get();
    }

    public ObjectProperty<LocalDate> pageLastDateProperty() {
        return pageLastDate;
    }

}
