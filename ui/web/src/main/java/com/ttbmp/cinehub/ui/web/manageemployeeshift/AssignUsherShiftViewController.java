package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.CreateShiftRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetCinemaListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetEmployeeListRequest;
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
    public String assignUsherShift(@CookieValue(value = "session") String sessionToken,
                                   @RequestParam(value = "idCinema") int cinemaId, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, selectedCinema));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        var shiftRequest = new NewShiftForm();
        model.addAttribute(ASSIGN_REQUEST, shiftRequest);
        return "/assign_usher_shift";
    }

    @PostMapping("/assign_usher_shift")
    public String assignUshShift(@CookieValue(value = "session") String sessionToken,
                                 @RequestParam(value = "idCinema") int cinemaId,
                                 @ModelAttribute(ASSIGN_REQUEST) NewShiftForm request,
                                 Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, selectedCinema));
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.createShift(new CreateShiftRequest(
                sessionToken,
                request.getEmployee().getId(),
                LocalDate.parse(request.getDate()),
                request.getStart(),
                request.getEnd())
        );
        var error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_usher_shift";

    }
}
