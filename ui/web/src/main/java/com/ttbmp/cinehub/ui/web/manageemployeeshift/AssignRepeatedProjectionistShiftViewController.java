package com.ttbmp.cinehub.ui.web.manageemployeeshift;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
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
public class AssignRepeatedProjectionistShiftViewController {

    private static final String ERROR = "error";
    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String SHIFT_ASSIGNED = "/shift_assigned";
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

    @GetMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjectionistShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, selectedCinema));
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        var request = new NewRepeatedShiftForm();
        model.addAttribute(ASSIGN_REQUEST, request);
        return ErrorHelper.returnView(response, model, "assign_repeated_projectionist_shift");
    }

    @PostMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjShift(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "idCinema") int cinemaId,
            @ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftForm request,
            Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        model.addAttribute("selectedEmployeeId", request.getEmployeeId());
        model.addAttribute("selectedHallId", request.getHallId());
        useCase.getCinemaList(new GetCinemaListRequest(sessionToken));
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getEmployeeList(new GetEmployeeListRequest(sessionToken, selectedCinema));
        var selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        var selectedHall = (HallDto) model.getAttribute("selectedHall");
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(
                sessionToken,
                request.getDate(),
                request.getDateRepeated(),
                request.getPreference(),
                selectedEmployee,
                request.getStart(),
                request.getEnd(),
                selectedHall));
        return ErrorHelper.returnView(response, model, "shift_assigned");
    }

}
