package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
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
    public void presentEmployeeList(GetEmployeeListResponse employeeList) {
        model.addAttribute("employeeList", employeeList.getEmployeeDtoList());
        model.addAttribute("projectionistList", employeeList.getEmployeeDtoList().stream()
                .filter(employeeDto -> employeeDto.getClass()
                        .equals(ProjectionistDto.class))
                .collect(Collectors.toList()));
        model.addAttribute("usherList", employeeList.getEmployeeDtoList().stream()
                .filter(employeeDto -> employeeDto.getClass()
                        .equals(UsherDto.class))
                .collect(Collectors.toList()));

        findEmployee(employeeList.getEmployeeDtoList());
    }

    @Override
    public void presentShiftList(GetShiftListResponse shiftList) {
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var dateSelected = shiftList.getDate();
        var cinemaSelected = shiftList.getCinemaId();
        var employeeList = (List<EmployeeDto>) model.getAttribute("employeeList");
        findShift(shiftList.getShiftDtoList());
        Map<EmployeeDto, List<ShiftDto>> employeeShiftListMap = new HashMap<>();
        for (var employee : employeeList) {
            employeeShiftListMap.put(
                    employee,
                    shiftList.getShiftDtoList().stream()
                            .filter(shift -> shift.getEmployee().equals(employee)
                                    && shift.getEmployee().getCinema().getId() == cinemaSelected
                                    && shift.getDate().get(temporalField) == dateSelected.get(temporalField))
                            .collect(Collectors.toList())
            );
        }
        model.addAttribute("shiftList", employeeShiftListMap);
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse listCinema) {
        model.addAttribute("cinemaList", listCinema.getCinemaList());
        if (model.getAttribute("idCinema") != null) {
            var idCinema = (int) model.getAttribute("idCinema");
            for (var cinemaDto : listCinema.getCinemaList()) {
                if (cinemaDto.getId() == idCinema) {
                    model.addAttribute("selectedCinema", cinemaDto);
                    model.addAttribute("hallList", cinemaDto.getHalList());
                }
            }
        }
        if (model.getAttribute("selectedHallId") != null) {
            var hallDtoList = (List<HallDto>) model.getAttribute("hallList");
            var hallId = (int) model.getAttribute("selectedHallId");
            assert hallDtoList != null;
            for (var hallDto : hallDtoList) {
                if (hallDto.getId() == hallId) {
                    model.addAttribute("selectedHall", hallDto);
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
    public void presentRepeatShift(ShiftRepeatResponse response) {
        //on the web side there is the reload of the page so you don't have to submit any changes
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        model.addAttribute("shiftCreated", response.getShiftDto());
    }

    @Override
    public void presentInvalidGetCinemaListRequest(GetCinemaListRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentCinemaListNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentCreateShiftError(Throwable error) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, error.getMessage());
    }

    @Override
    public void presentInvalidEmployeeListRequest(GetEmployeeListRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentEmployeeListNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidModifyShiftListRequest(ShiftModifyRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentModifyShiftNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, error.getMessage());
    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentCreateShiftNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));

    }

    @Override
    public void presentRepeatedShiftNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentGetShiftListNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
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

    private void findShift(List<ShiftDto> shiftList) {
        if (model.getAttribute("shiftId") != null) {
            var shiftId = (int) model.getAttribute("shiftId");
            for (var shiftDto : shiftList) {
                if (shiftDto.getId() == shiftId) {
                    model.addAttribute("selectedShift", shiftDto);
                    model.addAttribute("selectedEmployee", shiftDto.getEmployee());
                }
            }
        }
    }

}
