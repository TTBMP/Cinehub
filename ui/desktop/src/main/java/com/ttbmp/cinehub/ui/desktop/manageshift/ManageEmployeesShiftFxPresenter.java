package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * @author Massimo Mazzetti
 */
public class ManageEmployeesShiftFxPresenter implements ManageEmployeesShiftPresenter {

    private final ManageEmployeesShiftViewModel viewModel;

    public ManageEmployeesShiftFxPresenter(ManageEmployeesShiftViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentEmployeeList(GetEmployeeListResponse response) {
        List<EmployeeShiftWeek> employeeShiftWeekList = new ArrayList<>();
        for (var employeeDto : response.getEmployeeDtoList()) {
            var weekMap = new EnumMap<DayOfWeek, Day>(DayOfWeek.class);
            initializeWeekMap(weekMap, employeeDto);
            employeeShiftWeekList.add(new EmployeeShiftWeek(employeeDto, weekMap));
        }
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeekList);
    }

    @Override
    public void presentShiftList(GetShiftListResponse response) {
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        for (var employeeShiftWeek : viewModel.getEmployeeShiftWeekList()) {
            initializeWeekMap(employeeShiftWeek.getWeekMap(), employeeShiftWeek.getEmployeeDto());
            var index = viewModel.getEmployeeShiftWeekList().indexOf(employeeShiftWeek);
            viewModel.getEmployeeShiftWeekList().set(index, employeeShiftWeek);
        }
        for (var shift : response.getShiftDtoList()) {
            for (var employeeShiftWeek : viewModel.getEmployeeShiftWeekList()) {
                if (employeeShiftWeek.getEmployeeDto().getId().equals(shift.getEmployee().getId())
                        && viewModel.getSelectedWeek().getYear() == shift.getDate().getYear()
                        && viewModel.getSelectedWeek().get(temporalField) == shift.getDate().get(temporalField)) {
                    var index = viewModel.getEmployeeShiftWeekList().indexOf(employeeShiftWeek);
                    employeeShiftWeek.getWeekMap().get(shift.getDate().getDayOfWeek()).getShiftList().add(shift);
                    viewModel.getEmployeeShiftWeekList().set(index, employeeShiftWeek);
                }
            }
        }
    }

    private void initializeWeekMap(Map<DayOfWeek, Day> weekMap, EmployeeDto employeeDto) {
        var firstDayOfWeek = viewModel.getSelectedWeek().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        for (var dayOfWeek : DayOfWeek.values()) {
            var date = firstDayOfWeek.plusDays((long) dayOfWeek.getValue() - 1);
            weekMap.put(dayOfWeek, new Day(date, new ArrayList<>(), employeeDto));
        }
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse response) {
        viewModel.getCinemaList().setAll(response.getCinemaList());
    }

    @Override
    public void presentSaveShift() {
        var savedShift = viewModel.getShiftCreated();
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        employeeShiftWeeks.forEach(e -> {
            if (e.getEmployeeDto().equals(savedShift.getEmployee())
                    && savedShift.getDate().get(temporalField) == viewModel.getSelectedWeek().get(temporalField)
                    && savedShift.getDate().getYear() == viewModel.getSelectedWeek().getYear()) {
                e.getWeekMap().get(savedShift.getDate().getDayOfWeek())
                        .getShiftList()
                        .add(savedShift);
            }
        });
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentDeleteShift() {
        var deleteShift = viewModel.getSelectedShift();
        var employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        employeeShiftWeeks.forEach(e -> {
            if (e.getEmployeeDto().equals(deleteShift.getEmployee())) {
                e.getWeekMap().get(deleteShift.getDate().getDayOfWeek())
                        .getShiftList()
                        .removeIf(s -> s.equals(deleteShift));
            }
        });
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        var shiftList = response.getShiftDto();
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        for (var savedShift : shiftList) {
            employeeShiftWeeks.forEach(employeeShiftWeek -> {
                if (employeeShiftWeek.getEmployeeDto().equals(savedShift.getEmployee()) &&
                        savedShift.getDate().get(temporalField) == viewModel.getSelectedWeek().get(temporalField) &&
                        savedShift.getDate().getYear() == viewModel.getSelectedWeek().getYear()) {
                    var dayOfWeek = savedShift.getDate().getDayOfWeek();
                    employeeShiftWeek
                            .getWeekMap()
                            .get(dayOfWeek)
                            .getShiftList()
                            .add(savedShift);
                }
            });
        }
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        viewModel.setErrorAssignVisibility(false);
        viewModel.setShiftCreated(response.getShiftDto());
    }

    @Override
    public void presentInvalidGetCinemaListRequest(GetCinemaListRequest request) {

    }

    @Override
    public void presentCinemaListNullRequest() {
        viewModel.errorDaoProperty().setValue("Error with operation get cinema list");
    }

    @Override
    public void presentCreateShiftError(Throwable error) {
        viewModel.errorProperty().setValue(error.getMessage());
        viewModel.setErrorAssignVisibility(true);
    }

    @Override
    public void presentInvalidEmployeeListRequest(GetEmployeeListRequest request) {
        if (request.getErrorList().contains(GetEmployeeListRequest.MISSING_CINEMA)) {
            viewModel.errorDaoProperty().setValue(GetEmployeeListRequest.MISSING_CINEMA.getMessage());
        }
    }

    @Override
    public void presentEmployeeListNullRequest() {
        viewModel.errorDaoProperty().setValue("Error with operation get employee list");
    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        viewModel.errorDaoProperty().setValue("Error with operation delete shift");
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation delete shift");
    }

    @Override
    public void presentInvalidModifyShiftListRequest(ShiftModifyRequest request) {
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_EMPLOYEE)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_SHIFT)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_DATE)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_DATE.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_HALL)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_HALL.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_START)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftModifyRequest.MISSING_END)) {
            viewModel.errorProperty().setValue(ShiftModifyRequest.MISSING_END.getMessage());
        }

    }

    @Override
    public void presentModifyShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation modify shift");
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        viewModel.errorProperty().setValue("IMPOSSIBLE MODIFY SHIFT");
        viewModel.setErrorAssignVisibility(true);
    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_DATE)) {
            viewModel.errorProperty().setValue(CreateShiftRequest.MISSING_DATE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_EMPLOYEE)) {
            viewModel.errorProperty().setValue(CreateShiftRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_START)) {
            viewModel.errorProperty().setValue(CreateShiftRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_END)) {
            viewModel.errorProperty().setValue(CreateShiftRequest.MISSING_END.getMessage());
        }
    }

    @Override
    public void presentCreateShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation create shift");
    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {
        var error = "";
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_EMPLOYEE)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_OPTION)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_OPTION.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START_SHIFT)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_START_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END_SHIFT)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_END_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_HALL)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_HALL.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.PERIOD_ERROR)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.PERIOD_ERROR.getMessage());
        }
        viewModel.errorAssignVisibilityProperty().setValue(true);
    }

    @Override
    public void presentRepeatedShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation save repeated shift");
    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_CINEMA)) {
            viewModel.errorProperty().setValue(GetShiftListRequest.MISSING_CINEMA.getMessage());
        }
        if (request.getErrorList().contains(GetShiftListRequest.MISSING_START)) {
            viewModel.errorProperty().setValue(GetShiftListRequest.MISSING_START.getMessage());
        }
    }

    @Override
    public void presentGetShiftListNullRequest() {
        viewModel.errorProperty().setValue("Error with operation get shift List");
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        viewModel.errorDaoProperty().setValue(e.getMessage());
    }

    @Override
    public void presentUnauthenticatedRequest() {

    }

}
