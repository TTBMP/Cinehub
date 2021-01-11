package com.ttbmp.cinehub.app.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.di.MockAppContainer;
import com.ttbmp.cinehub.app.di.ViewPersonalScheduleContainer;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
class PersonalScheduleTest {

    ViewPersonalScheduleContainer container = new MockAppContainer().getContainer(ViewPersonalScheduleContainer.class);

    @Test
    void getShiftList_GetShiftListOfDifferentDaysInTheSameMonth_ShiftListDoesNotChange() {
        LocalDate date = container.getViewModel().getDate();
        container.getUseCase().getShiftList(
                container.getViewModel().getCalendarPageFirstDate(),
                container.getViewModel().getCalendarPageLastDate()
        );
        List<ShiftDto> expectedList = new ArrayList<>(container.getViewModel().getShiftList());
        LocalDate newDate = date.getDayOfMonth() == 1 ?
                date.with(TemporalAdjusters.lastDayOfMonth()) :
                date.with(TemporalAdjusters.firstDayOfMonth());
        container.getViewModel().setDate(newDate);
        container.getUseCase().getShiftList(
                container.getViewModel().getCalendarPageFirstDate(),
                container.getViewModel().getCalendarPageLastDate()
        );
        boolean areListEquals = true;
        for (int i = 0; i < container.getViewModel().getShiftList().size(); i++) {
            ShiftDto element = container.getViewModel().getShiftList().get(i);
            ShiftDto expectedElement = expectedList.get(i);
            if (!element.getEmployeeName().equals(expectedElement.getEmployeeName())
                    && element.getDate().toString().equals(expectedElement.getDate().toString())
                    && element.getStart().toString().equals(expectedElement.getStart().toString())
                    && element.getEnd().toString().equals(expectedElement.endProperty().toString())) {
                areListEquals = false;
                break;
            }
        }
        Assertions.assertTrue(areListEquals);
    }
}