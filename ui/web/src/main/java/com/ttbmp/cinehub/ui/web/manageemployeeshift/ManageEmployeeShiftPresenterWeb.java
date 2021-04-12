package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;


public class ManageEmployeeShiftPresenterWeb implements ManageEmployeesShiftPresenter {

    private static final String ERROR = "error";
    private static final String ERROR_TEXT = "errorText";
    private final Model model;

    public ManageEmployeeShiftPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentShiftList(GetShiftListResponse shiftList) {
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

        LocalDate dateSelected = shiftList.getDate();
        int cinemaSelected = shiftList.getCinemaId();

        List<EmployeeDto> employeeList = shiftList.getShiftDtoList().stream()
                .map(ShiftDto::getEmployee)
                .filter(employee -> employee.getCinema().getId() == cinemaSelected)
                .distinct()
                .collect(Collectors.toList());

        List<EmployeeDto> tmp = new ArrayList<>();
        for (int i = 0, employeeListSize = employeeList.size(); i < employeeListSize; i++) {
            boolean duplicate = false;
            for (int j = 0; j < i; j++) {
                if (employeeList.get(i) != employeeList.get(j) && employeeList.get(i).equals(employeeList.get(j))) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                tmp.add(employeeList.get(i));
            }
        }
        employeeList = tmp;
        model.addAttribute("employeeList", employeeList);

        Map<EmployeeDto, List<ShiftDto>> employeeShiftListMap = new HashMap<>();
        for (EmployeeDto employee : employeeList) {
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
    }

    @Override
    public void presentHallList(GetHallListResponse listHall) {

        model.addAttribute("hallList", listHall.getListHall());
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
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, error.getMessage());
    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        if (request.getErrorList().contains(ShiftRequest.MISSING_SHIFT)) {
            model.addAttribute(ERROR_TEXT, ShiftRequest.MISSING_SHIFT.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, "Error with operation delete shift");
    }

    @Override
    public void presentDeleteShiftError(Throwable error) {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, error.getMessage());
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
        model.addAttribute(ERROR_TEXT, "Error with operation delete shift");

    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        model.addAttribute(ERROR, true);
        model.addAttribute(ERROR_TEXT, error.getMessage());
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
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_HALL)) {
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_HALL);
            model.addAttribute(ERROR_TEXT, CreateShiftRequest.MISSING_HALL);
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
        model.addAttribute(ERROR_TEXT,"Error with operation save repeated shift");
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_START)) {
            model.addAttribute(ERROR_TEXT,GetShiftListRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_CINEMA)) {
            model.addAttribute(ERROR_TEXT,GetShiftListRequest.MISSING_CINEMA.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentGetShiftListNullRequest() {
        model.addAttribute(ERROR_TEXT,"Error with operation get Shift List");
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentInvalidHallListRequest(GetHallListRequest request) {
        if (request.getErrorList().contains(GetHallListRequest.MISSING_HALL)) {
            model.addAttribute(ERROR_TEXT,GetHallListRequest.MISSING_HALL.getMessage());
        }
        model.addAttribute(ERROR, true);
    }

    @Override
    public void presentHallListNullRequest() {
        model.addAttribute(ERROR_TEXT,"Error with operation get Hall List");
        model.addAttribute(ERROR, true);
    }



}
