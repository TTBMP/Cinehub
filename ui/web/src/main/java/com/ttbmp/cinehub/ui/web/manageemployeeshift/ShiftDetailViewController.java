package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftModifyRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ShiftDetailViewController {

    private static final String ERROR = "error";

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

    @PostMapping("/shift_detail")
    public String shiftDetail(@ModelAttribute("selectedShift") NewShiftForm shift,
                              Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        model.addAttribute("shiftDetail", shift);
        model.addAttribute("data", LocalDate.parse(shift.getDate()));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute("idCinema", shift.getEmployee().getCinema().getId());
        useCase.getCinemaList();

        if (shift.isChange()) {

            if (shift.getHall() != null) {
                model.addAttribute("selectedHallId", shift.getHall().getId());
            }
            useCase.getCinemaList();
            var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
            var selectedHall = (HallDto) model.getAttribute("selectedHall");
            model.addAttribute("selectedEmployeeId", shift.getEmployee().getId());
            useCase.getEmployeeList(new GetEmployeeListRequest(selectedCinema));
            var selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");

            useCase.modifyShift(new ShiftModifyRequest(
                    selectedEmployee,
                    shift.getShiftId(),
                    LocalDate.parse(shift.getDate()),
                    shift.getStart(),
                    shift.getEnd(),
                    selectedHall
            ));
            var error = (boolean) model.getAttribute(ERROR);

            if (!error) {
                return "shift_modify";
            }

        }
        model.addAttribute("modifyRequest", new NewShiftForm());

        return "/shift_detail";

    }


}
