package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalSchedulePresenter;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ProjectionListReply;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply.ShiftListReply;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalSchedulePresenterWeb extends PresenterWeb implements ViewPersonalSchedulePresenter {

    public ViewPersonalSchedulePresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void presentGetShiftList(ShiftListReply result) {
        var date = ((LocalDate) model.getAttribute("date"));
        if (date == null) {
            model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
            return;
        }
        date = date.with(TemporalAdjusters.firstDayOfMonth())
                .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()));
        List<CalendarDay> calendarDayList = new ArrayList<>();
        for (var i = 0; i < 42; i++) {
            var finalDate = date;
            calendarDayList.add(new CalendarDay(
                    Integer.toString(date.getDayOfMonth()),
                    result.getShiftDtoList().stream()
                            .filter(s -> s.getDate().equals(finalDate))
                            .collect(Collectors.toList())
            ));
            date = date.plusDays(1);
        }
        model.addAttribute("employee", result.getEmployeeDto());
        model.addAttribute("calendarDayList", calendarDayList);
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply result) {
        model.addAttribute("projections", result.getProjectionDtoList());
    }

}
