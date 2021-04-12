package com.ttbmp.cinehub.ui.web.manageemployeeshift;


import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetHallListRequest;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AssignRepeatedProjectionistShiftViewController {

    private static final String ERROR = "error";
    private static final String EMPLOYEE_LIST = "employeeList";
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
    public String assignRepeatedProjectionistShift(@RequestParam(value = "idCinema") int cinemaId, Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        useCase.getHallList(new GetHallListRequest(cinemaId));
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), cinemaId));

        List<EmployeeDto> employeeComboBox = (List<EmployeeDto>) model.getAttribute("employeeList");

        model.addAttribute(EMPLOYEE_LIST, employeeComboBox.stream()
                .filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList()));


        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewRepeatedShiftForm request = new NewRepeatedShiftForm();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_repeated_projectionist_shift";
    }

    @PostMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjShift(@RequestParam(value = "idCinema") int cinemaId,
                                          @ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftForm request,
                                          Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        EmployeeDto selectedEmployee = null;
        HallDto selectedHall = null;

        useCase.getHallList(new GetHallListRequest(cinemaId));
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), cinemaId));

        List<EmployeeDto> employeeComboBox = (List<EmployeeDto>) model.getAttribute("employeeList");

        model.addAttribute(EMPLOYEE_LIST, employeeComboBox.stream()
                .filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList()));


        for (EmployeeDto employeeDto : employeeComboBox) {
            if (employeeDto.getId().equals(request.getEmployeeId())) {
                selectedEmployee = employeeDto;
            }
        }

        List<HallDto> hallList = (List<HallDto>) model.getAttribute("hallList");

        for (HallDto hallDto : hallList) {
            if (hallDto.getId() == request.getHallId()) {
                selectedHall = hallDto;
            }
        }
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(
                request.getDate(),
                request.getDateRepeated(),
                request.getPreference(),
                selectedEmployee,
                request.getStart(),
                request.getEnd(),
                selectedHall));
        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_repeated_projectionist_shift";
    }
}
