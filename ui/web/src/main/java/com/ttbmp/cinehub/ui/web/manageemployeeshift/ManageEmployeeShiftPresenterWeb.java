package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.employee.ProjectionistDto;
import com.ttbmp.cinehub.app.dto.employee.UsherDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.ui.Model;

import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageEmployeeShiftPresenterWeb implements ManageEmployeesShiftPresenter {

    private final Model model;

    public ManageEmployeeShiftPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentEmployeeList(GetEmployeeListReply reply) {
        var employeeList = new EmployeeListDto(reply.getEmployeeDtoList());
        model.addAttribute("employeeList", employeeList.getEmployeeList());
        model.addAttribute("projectionistList", employeeList.getEmployeeList().stream()
                .filter(employeeDto -> employeeDto.getClass()
                        .equals(ProjectionistDto.class))
                .collect(Collectors.toList()));
        model.addAttribute("usherList", employeeList.getEmployeeList().stream()
                .filter(employeeDto -> employeeDto.getClass()
                        .equals(UsherDto.class))
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
    public void presentNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidRequest(Request request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
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
