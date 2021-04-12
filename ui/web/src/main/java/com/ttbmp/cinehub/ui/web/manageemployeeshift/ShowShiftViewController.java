package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.GetCinemaForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String populateCinema(Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getCinemaList();

        model.addAttribute("cinemaSelected", false);
        GetCinemaForm form = new GetCinemaForm();
        model.addAttribute("getShiftListRequest", form);

        return "/manage_employee_shift";
    }

    @PostMapping("/manage_employee_shift")
    public String loadShift(@ModelAttribute("getShiftListRequest") GetCinemaForm form, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        useCase.getCinemaList();

        model.addAttribute("cinemaSelected", true);
        useCase.getShiftList(new GetShiftListRequest(form.getStart(), form.getCinemaId()));

        model.addAttribute("idCinema", form.getCinemaId());
        model.addAttribute("date", form.getStart());

        return "/manage_employee_shift";
    }

}