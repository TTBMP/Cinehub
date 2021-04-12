package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.dto.ShiftProjectionistDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetHallListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftModifyRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/shift_detail")
    public String shiftDetail(@RequestParam("shiftDate") LocalDate date,
                              @RequestParam("idCinema") int cinemaId,
                              @RequestParam("shiftId") int shiftId,
                              Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        ShiftDto selectedShift = null;
        EmployeeDto selectedEmployee = null;

        useCase.getShiftList(new GetShiftListRequest(date, cinemaId));
        List<EmployeeDto> employeeDtoList = (List<EmployeeDto>) model.getAttribute("employeeList");
        Map<EmployeeDto, List<ShiftDto>> employeeShiftMap = (Map<EmployeeDto, List<ShiftDto>>) model.getAttribute("shiftList");

        for (EmployeeDto employeeDto : employeeDtoList) {
            for (ShiftDto shiftDto : employeeShiftMap.get(employeeDto)) {
                if (shiftDto.getId() == shiftId) {
                    selectedShift = shiftDto;
                    selectedEmployee = shiftDto.getEmployee();
                }
            }
        }

        boolean projection = selectedShift instanceof ShiftProjectionistDto;

        useCase.getHallList(new GetHallListRequest(cinemaId));

        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewShiftForm request = new NewShiftForm();
        model.addAttribute("modifyRequest", request);
        return "/shift_detail";
    }

    @PostMapping("/shift_detail")
    public String modifyShift(@RequestParam("shiftDate") LocalDate date,
                              @RequestParam("idCinema") int cinemaId,
                              @RequestParam("shiftId") int shiftId,
                              @ModelAttribute("modifyRequest") NewShiftForm request,
                              Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        useCase.getHallList(new GetHallListRequest(cinemaId));
        HallDto selectedHall = null;
        List<HallDto> hallList = (List<HallDto>) model.getAttribute("hallList");

        for (HallDto hallDto : hallList) {
            if (hallDto.getId() == request.getHallId()) {
                selectedHall = hallDto;
            }
        }

        ShiftDto selectedShift = null;
        EmployeeDto selectedEmployee = null;

        useCase.getShiftList(new GetShiftListRequest(date, cinemaId));
        List<EmployeeDto> employeeDtoList = (List<EmployeeDto>) model.getAttribute("employeeList");
        Map<EmployeeDto, List<ShiftDto>> employeeShiftMap = (Map<EmployeeDto, List<ShiftDto>>) model.getAttribute("shiftList");

        for (EmployeeDto employeeDto : employeeDtoList) {
            for (ShiftDto shiftDto : employeeShiftMap.get(employeeDto)) {
                if (shiftDto.getId() == shiftId) {
                    selectedShift = shiftDto;
                    selectedEmployee = shiftDto.getEmployee();
                }
            }
        }

        boolean projection = selectedShift instanceof ShiftProjectionistDto;

        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));


        useCase.modifyShift(new ShiftModifyRequest(
                selectedEmployee,
                shiftId,
                request.getDate(),
                request.getInizio(),
                request.getEnd(),
                selectedHall
        ));

        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return "shift_modify";
        }
        return "/shift_detail";

    }
}
