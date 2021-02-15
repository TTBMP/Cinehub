package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ViewPersonalSchedulePresenterWeb implements ViewPersonalSchedulePresenter {

    private final Model model;

    public ViewPersonalSchedulePresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentGetShiftList(ShiftListReply result) {
        LocalDate date = ((LocalDate) model.getAttribute("date"))
                .with(TemporalAdjusters.firstDayOfMonth())
                .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()));
        List<CalendarDay> calendarDayList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            LocalDate finalDate = date;
            calendarDayList.add(new CalendarDay(
                    Integer.toString(date.getDayOfMonth()),
                    result.getShiftDtoList().stream()
                            .filter(s -> s.getDate().equals(finalDate))
                            .collect(Collectors.toList())
            ));
            date = date.plusDays(1);
        }
        model.addAttribute("calendarDayList", calendarDayList);
    }

    @Override
    public void presentInvalidShiftListRequest(ShiftListRequest request) {

    }

    @Override
    public void presentShiftListNullRequest() {

    }

    @Override
    public void presentGetProjectionList(ProjectionListReply result) {
        ProjectionListDto projectionForm = new ProjectionListDto(result.getProjectionDtoList());
        model.addAttribute("form", projectionForm);
    }

    @Override
    public void presentProjectionListNullRequest() {

    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {

    }

}
