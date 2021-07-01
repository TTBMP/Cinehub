package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetCinemaListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftModifyRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Controller
public class ShiftDetailViewController {


    @PostMapping("/shift_detail")
    public String shiftDetail(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @ModelAttribute("selectedShift") NewShiftForm shift,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("shiftDetail", shift);
        model.addAttribute("data", LocalDate.parse(shift.getDate()));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute("idCinema", shift.getEmployee().getCinema().getId());
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));

        if (shift.isChange()) {
            int hallId = -1;
            if (shift.getHall() != null) {
                hallId = shift.getHall().getId();
            }

            useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, shift.getEmployee().getCinema().getId()));
            useCase.modifyShift(new ShiftModifyRequest(
                    sessionToken,
                    shift.getShiftId(),
                    LocalDate.parse(shift.getDate()),
                    shift.getStart(),
                    shift.getEnd(),
                    hallId,
                    shift.getType()
            ));
            model.addAttribute("scope", "Shift Modify Successful");
            return ErrorHelper.returnView(response, model, "manage_employee_shift/notification");
        }
        model.addAttribute("modifyRequest", new NewShiftForm());
        return ErrorHelper.returnView(response, model, "manage_employee_shift/shift_detail");
    }

}
