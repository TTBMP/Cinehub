package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRepeatRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewRepeatedShiftForm;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@Controller
public class AssignRepeatedUsherShiftViewController {

    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String PREFERENCE_LIST = "preferenceList";

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
        return ErrorHelper.returnView(response, model, "manage_employee_shift/assign_repeated_usher_shift");
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
                LocalDate.parse(request.getDate()),
                LocalDate.parse(request.getDateRepeated()),
                request.getPreference(),
                request.getEmployeeId(),
                request.getStart(),
                request.getEnd(),
                -1));
        model.addAttribute("scope", "Shift Assigned");
        return ErrorHelper.returnView(response, model, "manage_employee_shift/notification");
    }

}
