package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ShiftListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleHandler;
import com.ttbmp.cinehub.ui.web.domain.Shift;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Fabio Buracchi
 */
@Controller
public class ViewPersonalScheduleViewController {

    @GetMapping("/schedule")
    public String showShiftList(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        if (date == null) {
            date = LocalDate.now();
        }
        model.addAttribute("date", date);
        model.addAttribute("selectedShift", new Shift());
        var useCase = new ViewPersonalScheduleHandler(new ViewPersonalSchedulePresenterWeb(model));
        useCase.getShiftList(new ShiftListRequest(
                sessionToken,
                date.with(TemporalAdjusters.firstDayOfMonth()),
                date.with(TemporalAdjusters.lastDayOfMonth())
        ));
        return ErrorHelper.returnView(response, model, "schedule");
    }

    @PostMapping("/schedule/detail")
    public String showShiftDetail(HttpServletResponse response, @ModelAttribute Shift shift, Model model) {
        model.addAttribute("shift", shift);
        return ErrorHelper.returnView(response, model, "schedule_detail");
    }

    @PostMapping("/schedule/detail/projectionist")
    public String showProjectionistShiftDetail(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            @ModelAttribute Shift shift, Model model) {
        model.addAttribute("shift", shift);
        var useCase = new ViewPersonalScheduleHandler(new ViewPersonalSchedulePresenterWeb(model));
        useCase.getShiftProjectionList(new ProjectionListRequest(
                sessionToken,
                shift.getId())
        );
        return ErrorHelper.returnView(response, model, "schedule_projectionist_detail");
    }

}
