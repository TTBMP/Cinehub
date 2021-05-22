package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Controller
public class ShowShiftViewController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/manage_employee_shift")
    public String populateCinema(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        model.addAttribute("cinemaSelected", false);
        var form = new GetCinemaForm();
        model.addAttribute("getShiftListRequest", form);
        return ErrorHelper.returnView(response, model, "manage_employee_shift");
    }

    @PostMapping("/manage_employee_shift")
    public String loadShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @ModelAttribute("getShiftListRequest") GetCinemaForm form, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", form.getCinemaId());
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        model.addAttribute("cinemaSelected", true);
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, selectedCinema));
        useCase.getShiftList(new GetShiftListRequest(
                sessionToken,
                selectedCinema,
                form.getStart().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                form.getStart().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        ));
        model.addAttribute("date", form.getStart());
        model.addAttribute("selectedShift", new NewShiftForm());
        return ErrorHelper.returnView(response, model, "manage_employee_shift");
    }

}
