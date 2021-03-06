package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteShiftViewController {

    @GetMapping("/delete_shift")
    public String deleteShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam("shiftId") int shiftId,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.deleteShift(new ShiftRequest(sessionToken, shiftId));
        model.addAttribute("scope", "Shift deleted");
        return ErrorHelper.returnView(response, model, "manage_employee_shift/notification");
    }

}
