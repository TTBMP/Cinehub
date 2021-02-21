package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ShiftListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleHandler;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleUseCase;
import com.ttbmp.cinehub.ui.web.domain.Shift;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class ViewPersonalScheduleViewController {

    private ViewPersonalScheduleUseCase useCase;

    @GetMapping("/schedule")
    public String showShiftList(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        useCase = new ViewPersonalScheduleHandler(new ViewPersonalSchedulePresenterWeb(model));
        if (date == null) {
            date = LocalDate.now();
        }
        model.addAttribute("date", date);
        model.addAttribute("selectedShift", new Shift());
        useCase.getShiftList(new ShiftListRequest(
                "",
                date.with(TemporalAdjusters.firstDayOfMonth()),
                date.with(TemporalAdjusters.lastDayOfMonth())
        ));
        return ErrorHelper.returnView(model, "schedule");
    }

    @PostMapping("/schedule/detail")
    public String showShiftDetail(@ModelAttribute Shift shift, Model model) {
        useCase = new ViewPersonalScheduleHandler(new ViewPersonalSchedulePresenterWeb(model));
        model.addAttribute("shift", shift);
        return ErrorHelper.returnView(model, "schedule_detail");
    }

    @PostMapping("/schedule/detail/projectionist")
    public String showProjectionistShiftDetail(@ModelAttribute Shift shift, Model model) {
        useCase = new ViewPersonalScheduleHandler(new ViewPersonalSchedulePresenterWeb(model));
        model.addAttribute("shift", shift);
        useCase.getShiftProjectionList(new ProjectionListRequest(
                "",
                shift.getId())
        );
        return ErrorHelper.returnView(model, "schedule_projectionist_detail");
    }

}
