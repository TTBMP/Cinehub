package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRepeatRequest;
import com.ttbmp.cinehub.domain.shift.ShiftRepeatingOption;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewRepeatedShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class AssignRepeatedUsherShiftViewController {

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

    @GetMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUsherShift(@RequestParam(value = "idCinema") int cinemaId, Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList();
        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");

        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));

        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        NewRepeatedShiftForm request = new NewRepeatedShiftForm();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_repeated_usher_shift";
    }

    @PostMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUshShift(@RequestParam(value = "idCinema") int cinemaId, @ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftForm request,
                                         Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        model.addAttribute("selectedEmployeeId", request.getEmployeeId());
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList();
        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");

        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));

        model.addAttribute("now", LocalDate.now().plusDays(1));

        EmployeeDto selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());

        useCase.saveRepeatedShift(new ShiftRepeatRequest(
                request.getDate(),
                request.getDateRepeated(),
                request.getPreference(),
                selectedEmployee,
                request.getStart(),
                request.getEnd(),
                null));
        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_repeated_usher_shift";
    }
}
