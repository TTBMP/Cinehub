package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.CreateShiftRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Controller
public class AssignUsherShiftViewController {

    private static final String ASSIGN_REQUEST = "assignRequest";

    @GetMapping("/assign_usher_shift")
    public String assignUsherShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);

        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, cinemaId));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        var shiftRequest = new NewShiftForm();
        model.addAttribute(ASSIGN_REQUEST, shiftRequest);
        return ErrorHelper.returnView(response, model, "manage_employee_shift/assign_usher_shift");
    }

    @PostMapping("/assign_usher_shift")
    public String assignUshShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId,
            @ModelAttribute(ASSIGN_REQUEST) NewShiftForm request,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, cinemaId));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.createShift(new CreateShiftRequest(
                sessionToken,
                request.getEmployee().getId(),
                LocalDate.parse(request.getDate()),
                request.getStart(),
                request.getEnd())
        );
        model.addAttribute("scope", "Shift Assigned");
        return ErrorHelper.returnView(response, model, "manage_employee_shift/notification");
    }

}
