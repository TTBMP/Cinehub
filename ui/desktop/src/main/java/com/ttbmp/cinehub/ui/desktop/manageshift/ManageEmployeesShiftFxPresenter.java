package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.Day;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

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
                if (employeeShiftWeek.getEmployeeDto().getId().equals(shift.getEmployeeId())
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
            if (e.getEmployeeDto().getId().equals(savedShift.getEmployeeId())
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
            if (e.getEmployeeDto().getId().equals(deleteShift.getEmployeeId())) {
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
                if (employeeShiftWeek.getEmployeeDto().getId().equals(savedShift.getEmployeeId()) &&
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
    public void presentCreateShiftError(Throwable error) {
        viewModel.errorProperty().setValue(error.getMessage());
        viewModel.setErrorAssignVisibility(true);
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        viewModel.errorProperty().setValue("IMPOSSIBLE MODIFY SHIFT");
        viewModel.setErrorAssignVisibility(true);
    }

    @Override
    public void presentNullRequest() {
        viewModel.setError("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.setError(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }


    @Override
    public void presentRepositoryError(RepositoryException e) {
        viewModel.errorDaoProperty().setValue(e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.errorDaoProperty().setValue(e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.errorDaoProperty().setValue(e.getMessage());
    }


}
