package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;

import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;


public class ManageEmployeeShiftPresenterWeb implements ManageEmployeesShiftPresenter {

    private final ManageEmployeeShiftViewModel viewModel;

    public ManageEmployeeShiftPresenterWeb(ManageEmployeeShiftViewModel manageEmployeeShiftViewModel) {
        this.viewModel = manageEmployeeShiftViewModel;
    }

    @Override
    public void presentShiftList(GetShiftListResponse shiftList) {
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

        List<EmployeeDto> employeeList = shiftList.getShiftDtoList().stream()
                .map(ShiftDto::getEmployee)
                .filter(employee -> employee.getCinema().getId() == viewModel.getSelectedCinema().getId())
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
        viewModel.setEmployeeDtoList(employeeList);

        Map<EmployeeDto, List<ShiftDto>> employeeShiftListMap = new HashMap<>();
        for (EmployeeDto employee : employeeList) {
            employeeShiftListMap.put(
                    employee,
                    shiftList.getShiftDtoList().stream()
                            .filter(shift -> shift.getEmployee().equals(employee)
                                    && shift.getEmployee().getCinema().getId() == viewModel.getSelectedCinema().getId()
                                    && shift.getDate().get(temporalField) == viewModel.getSelectedDate().get(temporalField))
                            .collect(Collectors.toList())
            );
        }
        viewModel.setEmployeeShiftMap(employeeShiftListMap);
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse listCinema) {
        viewModel.setCinemaDtoList(listCinema.getCinemaList());
    }

    @Override
    public void presentHallList(GetHallListResponse listHall) {
        viewModel.setHallDtoList(listHall.getListHall());
    }

    @Override
    public void presentSaveShift() {
        viewModel.setAssignError(false);
        viewModel.setModifyError(false);
    }

    @Override
    public void presentDeleteShift() {
        viewModel.setDeleteError(false);
    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        viewModel.setAssignError(false);
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        viewModel.setShiftCreated(response.getShiftDto());
    }

    @Override
    public void presentInvalidSaveShiftListRequest(ShiftRequest request) {
        if (request.getErrorList().contains(ShiftRequest.MISSING_SHIFT)) {
            viewModel.setAssignErrorText(ShiftRequest.MISSING_SHIFT.getMessage());
        }
        viewModel.setAssignError(true);
    }

    @Override
    public void presentSaveShiftNullRequest() {
        viewModel.setAssignErrorText("Error with operation save shift");
        viewModel.setAssignError(true);
    }

    @Override
    public void presentSaveShiftError(Throwable error) {
        viewModel.setAssignErrorText(error.getMessage());
        viewModel.setAssignError(true);
    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        if (request.getErrorList().contains(ShiftRequest.MISSING_SHIFT)) {
            viewModel.setDeleteErrorText(ShiftRequest.MISSING_SHIFT.getMessage());
        }
        viewModel.setDeleteError(true);
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        viewModel.setDeleteErrorText("Error with operation delete shift");
        viewModel.setDeleteError(true);
    }

    @Override
    public void presentDeleteShiftError(Throwable error) {
        viewModel.setDeleteErrorText(error.getMessage());
        viewModel.setDeleteError(true);
    }

    @Override
    public void presentSaveRepeatedShiftError(Throwable error) {
        viewModel.setAssignErrorText("Impossible Assign Repeated Shift");
        viewModel.setAssignError(true);
    }

    @Override
    public void presentModifyShiftNullRequest() {
        viewModel.setModifyErrorText("Error with operation modify shift");
        viewModel.setModifyError(true);
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        viewModel.setModifyErrorText(error.getMessage());
        viewModel.setModifyError(true);
    }

    @Override
    public void presentInvalidModifyRequest(ShiftModifyRequest request) {
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_OLD_SHIFT)) {
            viewModel.setModifyErrorText(ShiftModifyRequest.MISSING_OLD_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_NEW_SHIFT)) {
            viewModel.setModifyErrorText(ShiftModifyRequest.MISSING_NEW_SHIFT.getMessage());
        }
        viewModel.setModifyError(true);
    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_EMPLOYEE)) {
            viewModel.setAssignErrorText(CreateShiftRequest.MISSING_EMPLOYEE.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_DATE)) {
            viewModel.setAssignErrorText(CreateShiftRequest.MISSING_DATE.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.MISSING_DATE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_START)) {
            viewModel.setAssignErrorText(CreateShiftRequest.MISSING_START.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_END)) {
            viewModel.setAssignErrorText(CreateShiftRequest.MISSING_END.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_HALL)) {
            viewModel.setAssignErrorText(CreateShiftRequest.MISSING_HALL.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.MISSING_HALL.getMessage());
        }
        if(request.getErrorList().contains(CreateShiftRequest.DATE_ERROR)){
            viewModel.setAssignErrorText(CreateShiftRequest.DATE_ERROR.getMessage());
            viewModel.setModifyErrorText(CreateShiftRequest.DATE_ERROR.getMessage());
        }
        viewModel.setModifyError(true);
        viewModel.setAssignError(true);
    }

    @Override
    public void presentCreateShiftNullRequest() {
        viewModel.setAssignErrorText("Error with operation assign shift");
        viewModel.setAssignError(true);
        viewModel.setModifyErrorText("Error with operation modify shift");
        viewModel.setModifyError(true);
    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_SHIFT)) {
            viewModel.setAssignErrorText(ShiftRepeatRequest.MISSING_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START)) {
            viewModel.setAssignErrorText(ShiftRepeatRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END)) {
            viewModel.setAssignErrorText(ShiftRepeatRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_OPTION)) {
            viewModel.setAssignErrorText(ShiftRepeatRequest.MISSING_OPTION.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.PERIOD_NOT_VALID)) {
            viewModel.setAssignErrorText(ShiftRepeatRequest.PERIOD_NOT_VALID.getMessage());
        }
    }

    @Override
    public void presentRepeatedShiftNullRequest() {
        viewModel.setAssignErrorText("Error with operation save repeated shift");
        viewModel.setAssignError(true);
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_START)) {
            viewModel.setAssignErrorText(GetShiftListRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_CINEMA)) {
            viewModel.setAssignErrorText(GetShiftListRequest.MISSING_CINEMA.getMessage());
        }
        viewModel.setAssignError(true);

    }

    @Override
    public void presentGetShiftListNullRequest() {
        viewModel.setAssignErrorText("Error with operation get Shift List");
        viewModel.setAssignError(true);
    }

    @Override
    public void presentInvalidHallListRequest(GetHallListRequest request) {
        if (request.getErrorList().contains(GetHallListRequest.MISSING_HALL)) {
            viewModel.setAssignErrorText(GetHallListRequest.MISSING_HALL.getMessage());
        }
        viewModel.setAssignError(true);
    }

    @Override
    public void presentHallListNullRequest() {
        viewModel.setAssignErrorText("Error with operation get Hall List");
        viewModel.setAssignError(true);

    }


}
