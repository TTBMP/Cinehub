package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.CreateShiftRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AssignUsherShiftViewController {

    private static final String ERROR = "error";
    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String SHIFT_ASSIGNED = "/shift_assigned";


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

    @GetMapping("/assign_usher_shift")
    public String assignUsherShift(@RequestParam(value = "idCinema") int cinemaId, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList();
        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewShiftForm shiftRequest = new NewShiftForm();
        model.addAttribute(ASSIGN_REQUEST, shiftRequest);
        return "/assign_usher_shift";
    }

    @PostMapping("/assign_usher_shift")
    public String assignUshShift(@RequestParam(value = "idCinema") int cinemaId,
                                 @ModelAttribute(ASSIGN_REQUEST) NewShiftForm request,
                                 Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("selectedEmployeeId", request.getEmployeeId());
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList();
        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));
        EmployeeDto selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.getDate(), request.getInizio(), request.getEnd()));
        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_usher_shift";

    }
}
