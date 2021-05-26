package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetCinemaListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRepeatRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewRepeatedShiftForm;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class AssignRepeatedUsherShiftViewController {

    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String PREFERENCE_LIST = "preferenceList";

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

    @GetMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUsherShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, cinemaId));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        var request = new NewRepeatedShiftForm();
        model.addAttribute(ASSIGN_REQUEST, request);
        return ErrorHelper.returnView(response, model, "assign_repeated_usher_shift");
    }

    @PostMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUshShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId,
            @ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftForm request,
            Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, cinemaId));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        useCase.createRepeatedShift(new ShiftRepeatRequest(
                sessionToken,
                request.getDate(),
                request.getDateRepeated(),
                request.getPreference(),
                request.getEmployeeId(),
                request.getStart(),
                request.getEnd(),
                -1));
        return ErrorHelper.returnView(response, model, "shift_assigned");
    }

}
