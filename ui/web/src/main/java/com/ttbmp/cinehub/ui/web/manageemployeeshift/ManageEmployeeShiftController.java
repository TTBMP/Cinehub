package com.ttbmp.cinehub.ui.web.manageemployeeshift;


import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.dto.ShiftProjectionistDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ManageEmployeeShiftController {

    private static final ManageEmployeesShiftUseCase useCase = UseCase.manageEmployeeUseCase;
    private final ManageEmployeeShiftViewModel viewModel = UseCase.getViewModel();
    private Map<EmployeeDto, List<Object>> employeeShiftMap= new HashMap<>();
    private boolean projection;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/manage_employee_shift")
    public String populateCinema(Model model) {
        useCase.getCinemaList();
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        model.addAttribute("date", LocalDate.now());
        GetShiftListRequest request = new GetShiftListRequest();
        model.addAttribute("getShiftListRequest", request);
        return "/manage_employee_shift";
    }

    @PostMapping("/manage_employee_shift")
    public String submitForm(@ModelAttribute("getShiftListRequest") GetShiftListRequest request, Model model) {
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        viewModel.setSelectedCinema(request.getCinemaId());
        viewModel.setSelectedDate(request.getStart());
        useCase.getShiftList(request);
        employeeShiftMap = viewModel.getEmployeeShiftMap();
        model.addAttribute("shiftList", viewModel.getEmployeeShiftMap());
        return "/manage_employee_shift";
    }

    @GetMapping("/shift_detail/{idEmployee}/{idShift}")
    public String shiftDetail(
            @PathVariable int idEmployee,
            @PathVariable int idShift,
            Model model) {
        List<EmployeeDto> employeeDtoList = viewModel.getEmployeeDtoList();
        employeeShiftMap = viewModel.getEmployeeShiftMap();

        EmployeeDto selectedEmployee = employeeDtoList.get(idEmployee);
        Object selectedShift = employeeShiftMap.get(selectedEmployee).get(idShift);
        if(selectedShift instanceof ShiftProjectionistDto){
             projection = true;
        }else{
            projection = false;
        }
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);

        return "/shift_detail";
    }

}