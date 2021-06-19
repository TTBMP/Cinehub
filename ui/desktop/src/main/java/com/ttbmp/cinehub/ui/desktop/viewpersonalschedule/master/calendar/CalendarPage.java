package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Value;
import lombok.experimental.Accessors;

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
@Value
public class CalendarPage {

    @Accessors(fluent = false)
    ObservableList<Map<DayOfWeek, CalendarDay>> shiftWeekList = FXCollections.observableArrayList();

    ObjectProperty<LocalDate> pageFirstDateProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> pageLastDateProperty = new SimpleObjectProperty<>();

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
        pageFirstDateProperty.bind(ObjectBindings.map(dateList.get(0), identity()));
        pageLastDateProperty.bind(ObjectBindings.map(dateList.get(41), identity()));
    }

}
