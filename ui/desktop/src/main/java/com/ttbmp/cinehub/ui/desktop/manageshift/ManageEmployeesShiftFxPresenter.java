package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
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
    public void presentEmployeeList(GetEmployeeListReply reply) {
        List<EmployeeShiftWeek> employeeShiftWeekList = new ArrayList<>();
        for (var employeeDto : reply.getEmployeeDtoList()) {
            var weekMap = new EnumMap<DayOfWeek, Day>(DayOfWeek.class);
            initializeWeekMap(weekMap, employeeDto);
            employeeShiftWeekList.add(new EmployeeShiftWeek(employeeDto, weekMap));
        }
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeekList);
    }

    @Override
    public void presentShiftList(GetShiftListReply reply) {
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        for (var employeeShiftWeek : viewModel.getEmployeeShiftWeekList()) {
            initializeWeekMap(employeeShiftWeek.getWeekMap(), employeeShiftWeek.getEmployeeDto());
            var index = viewModel.getEmployeeShiftWeekList().indexOf(employeeShiftWeek);
            viewModel.getEmployeeShiftWeekList().set(index, employeeShiftWeek);
        }
        for (var shift : reply.getShiftDtoList()) {
            for (var employeeShiftWeek : viewModel.getEmployeeShiftWeekList()) {
                if (employeeShiftWeek.getEmployeeDto().getId().equals(shift.getEmployeeId())
                        && viewModel.selectedWeekProperty().get().getYear() == shift.getDate().getYear()
                        && viewModel.selectedWeekProperty().get().get(temporalField) == shift.getDate().get(temporalField)) {
                    var index = viewModel.getEmployeeShiftWeekList().indexOf(employeeShiftWeek);
                    employeeShiftWeek.getWeekMap().get(shift.getDate().getDayOfWeek()).getShiftList().add(shift);
                    viewModel.getEmployeeShiftWeekList().set(index, employeeShiftWeek);
                }
            }
        }
    }

    private void initializeWeekMap(Map<DayOfWeek, Day> weekMap, EmployeeDto employeeDto) {
        var firstDayOfWeek = viewModel.selectedWeekProperty().get().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        for (var dayOfWeek : DayOfWeek.values()) {
            var date = firstDayOfWeek.plusDays((long) dayOfWeek.getValue() - 1);
            weekMap.put(dayOfWeek, new Day(date, new ArrayList<>(), employeeDto));
        }
    }

    @Override
    public void presentCinemaList(GetCinemaListReply reply) {
        viewModel.getCinemaList().setAll(reply.getCinemaList());
    }

    @Override
    public void presentSaveShift() {
        var savedShift = viewModel.shiftCreatedProperty().get();
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        employeeShiftWeeks.forEach(e -> {
            if (e.getEmployeeDto().getId().equals(savedShift.getEmployeeId())
                    && savedShift.getDate().get(temporalField) == viewModel.selectedWeekProperty().get().get(temporalField)
                    && savedShift.getDate().getYear() == viewModel.selectedWeekProperty().get().getYear()) {
                e.getWeekMap().get(savedShift.getDate().getDayOfWeek())
                        .getShiftList()
                        .add(savedShift);
            }
        });
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentDeleteShift() {
        var deleteShift = viewModel.selectedShiftProperty().get();
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
    public void presentRepeatShift(ShiftRepeatReply reply) {
        var shiftList = reply.getShiftDto();
        var temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        var employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        for (var savedShift : shiftList) {
            employeeShiftWeeks.forEach(employeeShiftWeek -> {
                if (employeeShiftWeek.getEmployeeDto().getId().equals(savedShift.getEmployeeId()) &&
                        savedShift.getDate().get(temporalField) == viewModel.selectedWeekProperty().get().get(temporalField) &&
                        savedShift.getDate().getYear() == viewModel.selectedWeekProperty().get().getYear()) {
                    var dayOfWeek = savedShift.getDate().getDayOfWeek();
                    employeeShiftWeek
                            .getWeekMap()
                            .get(dayOfWeek)
                            .getShiftList()
                            .add(savedShift);
                }
            });
        }
        viewModel.errorAssignVisibleProperty().set(false);
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentCreateShift(CreateShiftReply reply) {
        viewModel.errorAssignVisibleProperty().set(false);
        viewModel.shiftCreatedProperty().set(reply.getShiftDto());
    }

    @Override
    public void presentCreateShiftError(Throwable error) {
        viewModel.errorMessageProperty().setValue(error.getMessage());
        viewModel.errorAssignVisibleProperty().set(true);
    }

    @Override
    public void presentModifyShiftError(Throwable error) {
        viewModel.errorMessageProperty().setValue("IMPOSSIBLE MODIFY SHIFT");
        viewModel.errorAssignVisibleProperty().set(true);
    }

    @Override
    public void presentSendEmailServiceException(EmailServiceException error) {
        viewModel.errorMessageProperty().setValue(error.getMessage());
    }

    @Override
    public void presentNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.errorMessageProperty().setValue(request.getErrorList().stream()
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
