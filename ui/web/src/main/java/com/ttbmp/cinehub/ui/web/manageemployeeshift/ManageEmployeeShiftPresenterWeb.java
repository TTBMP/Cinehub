package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import org.springframework.ui.Model;

import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public class ManageEmployeeShiftPresenterWeb implements ManageEmployeesShiftPresenter {

    private static final String ERROR = "error";
    private static final String ERROR_TEXT = "errorText";
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
        model.addAttribute(ERROR, false);
        model.addAttribute(ERROR, false);
    }

    @Override
    public void presentDeleteShift() {
        model.addAttribute(ERROR, false);
    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        model.addAttribute(ERROR, false);
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        model.addAttribute("shiftCreated", response.getShiftDto());
    }

    @Override
    public void presentCreateShiftError(Throwable error) {
        presentError(error);
    }

    @Override
    public void presentInvalidEmployeeListRequest(GetEmployeeListRequest request) {
        if (request.getErrorList().contains(GetEmployeeListRequest.MISSING_CINEMA)) {
            model.addAttribute(ERROR_TEXT, GetEmployeeListRequest.MISSING_CINEMA.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentEmployeeListNullRequest() {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, "Error with operation get Employee list shift");
    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, "Error with operation delete shift");
    }

    @Override
    public void presentInvalidModifyShiftListRequest(ShiftModifyRequest request) {
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_SHIFT)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_START)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_END)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_DATE)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_DATE.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_HALL)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_HALL.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_EMPLOYEE)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.ERROR_TIME)) {
            model.addAttribute(ERROR_TEXT, ShiftModifyRequest.ERROR_TIME.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentModifyShiftNullRequest() {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, "Error with operation modify shift");

    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        presentError(error);
    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_EMPLOYEE)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_EMPLOYEE.getMessage());
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_DATE)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_DATE.getMessage());
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_DATE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_START)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_START.getMessage());
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_END)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_END.getMessage());
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.DATE_ERROR)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.DATE_ERROR.getMessage());
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.DATE_ERROR.getMessage());
        }
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR, true);

    }

    @Override
    public void presentCreateShiftNullRequest() {
        model.addAttribute(ERROR_TEXT, "Error with operation assign shift");
        model.addAttribute(ERROR_TEXT, "Error with operation modify shift");
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START_SHIFT)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_START_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_OPTION)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_OPTION.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END_SHIFT)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_END_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_EMPLOYEE)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_HALL)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.MISSING_HALL.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.PERIOD_ERROR)) {
            model.addAttribute(ERROR_TEXT, ShiftRepeatRequest.PERIOD_ERROR.getMessage());
        }
        model.addAttribute(ERROR, true);

    }

    @Override
    public void presentRepeatedShiftNullRequest() {
        model.addAttribute(ERROR_TEXT, "Error with operation save repeated shift");
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_START)) {
            model.addAttribute(ERROR_TEXT, GetShiftListRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_CINEMA)) {
            model.addAttribute(ERROR_TEXT, GetShiftListRequest.MISSING_CINEMA.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentGetShiftListNullRequest() {
        model.addAttribute(ERROR_TEXT, "Error with operation get Shift List");
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        presentError(e);
    }

    private void presentError(Throwable error) {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, error.getMessage());
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
