package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.EmployeeListDto;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageEmployeeShiftPresenterWeb extends PresenterWeb implements ManageEmployeesShiftPresenter {

    public ManageEmployeeShiftPresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void presentEmployeeList(GetEmployeeListReply reply) {
        var employeeList = new EmployeeListDto(reply.getEmployeeDtoList());
        model.addAttribute("employeeList", employeeList.getEmployeeList());
        model.addAttribute("projectionistList", employeeList.getEmployeeList().stream()
                .filter(employeeDto -> employeeDto.getRole().equals(EmployeeDto.EmployeeRole.PROJECTIONIST))
                .collect(Collectors.toList()));
        model.addAttribute("usherList", employeeList.getEmployeeList().stream()
                .filter(employeeDto -> employeeDto.getRole().equals(EmployeeDto.EmployeeRole.USHER))
                .collect(Collectors.toList()));
        findEmployee(reply.getEmployeeDtoList());
    }

    @Override
    public void presentShiftList(GetShiftListReply reply) {
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var dateSelected = reply.getDate();
        var cinemaSelected = reply.getCinemaId();
        var employeeList = (List<EmployeeDto>) model.getAttribute("employeeList");
        Map<EmployeeDto, List<ShiftDto>> employeeShiftListMap = new HashMap<>();
        for (var employee : employeeList) {
            employeeShiftListMap.put(
                    employee,
                    reply.getShiftDtoList().stream()
                            .filter(shift -> shift.getEmployeeId().equals(employee.getId())
                                    && employee.getCinema().getId() == cinemaSelected
                                    && shift.getDate().get(temporalField) == dateSelected.get(temporalField))
                            .collect(Collectors.toList())
            );
        }
        model.addAttribute("employeeShiftListMap", employeeShiftListMap);
    }

    @Override
    public void presentCinemaList(GetCinemaListReply reply) {
        model.addAttribute("cinemaList", reply.getCinemaList());
        if (model.getAttribute("idCinema") != null) {
            var idCinema = (int) model.getAttribute("idCinema");
            for (var cinemaDto : reply.getCinemaList()) {
                if (cinemaDto.getId() == idCinema) {
                    model.addAttribute("selectedCinema", cinemaDto);
                    model.addAttribute("hallList", cinemaDto.getHalList());
                }
            }
        }
    }

    @Override
    public void presentSaveShift() {
        //on the web side there is the reload of the page so you don't have to submit any changes
    }

    @Override
    public void presentDeleteShift() {
        //on the web side there is the reload of the page so you don't have to submit any changes
    }

    @Override
    public void presentRepeatShift(ShiftRepeatReply reply) {
        //on the web side there is the reload of the page so you don't have to submit any changes
    }

    @Override
    public void presentCreateShift(CreateShiftReply reply) {
        model.addAttribute("shiftCreated", reply.getShiftDto());
    }

    @Override
    public void presentCreateShiftError(Throwable error) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, error.getMessage());
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, error.getMessage());
    }

    @Override
    public void presentSendEmailServiceException(EmailServiceException error) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, error.getMessage());
    }

    private void findEmployee(List<EmployeeDto> employeeList) {
        if (model.getAttribute("selectedEmployeeId") != null) {
            var employeeId = (String) model.getAttribute("selectedEmployeeId");
            for (var employeeDto : employeeList) {
                if (employeeDto.getId().equals(employeeId)) {
                    model.addAttribute("selectedEmployee", employeeDto);
                }
            }
        }
    }

}
