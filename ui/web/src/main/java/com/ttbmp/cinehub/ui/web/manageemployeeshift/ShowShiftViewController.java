package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetCinemaListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.GetCinemaForm;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class ShowShiftViewController {


    @GetMapping("/manage_employee_shift")
    public String populateCinema(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        model.addAttribute("cinemaSelected", false);
        var form = new GetCinemaForm();
        form.setStart(LocalDate.now().toString());
        model.addAttribute("getShiftListRequest", form);
        return ErrorHelper.returnView(response, model, "manage_employee_shift/manage_employee_shift");
    }

    @PostMapping("/manage_employee_shift")
    public String loadShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @ModelAttribute("getShiftListRequest") GetCinemaForm form, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        model.addAttribute("cinemaSelected", true);
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, form.getCinemaId()));
        useCase.getShiftList(new GetShiftListRequest(
                sessionToken,
                form.getCinemaId(),
                LocalDate.parse(form.getStart()).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                LocalDate.parse(form.getStart()).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        ));
        model.addAttribute("idCinema", form.getCinemaId());
        model.addAttribute("date", form.getStart());
        model.addAttribute("selectedShift", new NewShiftForm());
        return ErrorHelper.returnView(response, model, "manage_employee_shift/manage_employee_shift");
    }

}
