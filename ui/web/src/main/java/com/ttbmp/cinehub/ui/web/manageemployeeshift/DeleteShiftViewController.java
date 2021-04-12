package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class DeleteShiftViewController {

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

    @GetMapping("/delete_shift")
    public String deleteShift(@RequestParam("shiftDate") LocalDate date,
                              @RequestParam("idCinema") int cinemaId,
                              @RequestParam("shiftId") int shiftId,
                              Model model) {
        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        ShiftDto selectedShift = null;

        useCase.getShiftList(new GetShiftListRequest(date, cinemaId));
        List<EmployeeDto> employeeDtoList = (List<EmployeeDto>) model.getAttribute("employeeList");
        Map<EmployeeDto, List<ShiftDto>> employeeShiftMap = (Map<EmployeeDto, List<ShiftDto>>) model.getAttribute("shiftList");

        for (EmployeeDto employeeDto : employeeDtoList) {
            for (ShiftDto shiftDto : employeeShiftMap.get(employeeDto)) {
                if (shiftDto.getId() == shiftId) {
                    selectedShift = shiftDto;
                }
            }
        }

        useCase.deleteShift(new ShiftRequest(selectedShift));

        return "/delete_shift";
    }
}
