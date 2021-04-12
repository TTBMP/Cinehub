package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.dto.UsherDto;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AssignUsherShiftViewController {

    private static final String ERROR = "error";
    private static final String EMPLOYEE_LIST = "employeeList";
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

        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), cinemaId));
        List<EmployeeDto> employeeComboBox = (List<EmployeeDto>) model.getAttribute("employeeList");

        model.addAttribute(EMPLOYEE_LIST, employeeComboBox.stream()
                .filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList()));

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

        EmployeeDto selectedEmployee = null;
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), cinemaId));
        List<EmployeeDto> employeeComboBox = (List<EmployeeDto>) model.getAttribute("employeeList");
        assert employeeComboBox != null;
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox.stream()
                .filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList()));

        model.addAttribute("now", LocalDate.now().plusDays(1));

        for (EmployeeDto employeeDto : employeeComboBox) {
            if (employeeDto.getId().equals(request.getEmployeeId())) {
                selectedEmployee = employeeDto;
            }
        }

        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.getDate(), request.getInizio(), request.getEnd()));
        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_usher_shift";

    }
}
