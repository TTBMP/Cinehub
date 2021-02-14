package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.app.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.DayWeek;
import com.ttbmp.cinehub.ui.desktop.manageshift.table.EmployeeShiftWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
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
    public void presentShiftList(GetShiftListResponse shiftList) {
        viewModel.getEmployeeShiftWeekList().setAll(getEmployeeShiftWeekList(
                new GetShiftListResponse(
                        shiftList.getShiftDtoList(),
                        shiftList.getDate(),
                        shiftList.getCinema()
                )
        ));
    }

    @Override
    public void presentCinemaList(GetCinemaListResponse listCinema) {
        viewModel.getCinemaList().setAll(listCinema.getCinemaList());
    }

    @Override
    public void presentHallList(GetHallListResponse listHall) {
        viewModel.getHallList().setAll(listHall.getListHall());
    }


    @Override
    public void presentSaveShift() {
        Shift savedShift = ShiftDataMapper.mapToEntity(viewModel.getShiftCreated());
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        List<EmployeeShiftWeek> employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        employeeShiftWeeks.forEach(e -> {
            if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(savedShift.getEmployee())
                    && LocalDate.parse(savedShift.getDate()).get(temporalField) == viewModel.getSelectedWeek().get(temporalField)
                    && LocalDate.parse(savedShift.getDate()).getYear() == viewModel.getSelectedWeek().getYear()) {
                e.getWeekMap().get(LocalDate.parse(savedShift.getDate()).getDayOfWeek())
                        .getShiftList()
                        .add(ShiftDataMapper.mapToDto(savedShift));
            }
        });
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);
    }

    @Override
    public void presentDeleteShift() {
        Shift deleteShift = ShiftDataMapper.mapToEntity(viewModel.getSelectedShift());
        List<EmployeeShiftWeek> employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        employeeShiftWeeks.forEach(e -> {
            if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(deleteShift.getEmployee())) {
                e.getWeekMap().get(LocalDate.parse(deleteShift.getDate()).getDayOfWeek())
                        .getShiftList()
                        .removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
            }
        });
        viewModel.getEmployeeShiftWeekList().setAll(employeeShiftWeeks);

    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        List<ShiftDto> shiftList = response.getShiftDto();
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        List<EmployeeShiftWeek> employeeShiftWeeks = new ArrayList<>(viewModel.getEmployeeShiftWeekList());
        for (ShiftDto savedShift : shiftList) {
            employeeShiftWeeks.forEach(employeeShiftWeek -> {
                if (employeeShiftWeek.getEmployeeDto().equals(savedShift.getEmployee()) &&
                        savedShift.getDate().get(temporalField) == viewModel.getSelectedWeek().get(temporalField) &&
                        savedShift.getDate().getYear() == viewModel.getSelectedWeek().getYear()) {
                    DayOfWeek dayOfWeek = savedShift.getDate().getDayOfWeek();
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
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {
        if (request.getErrorList().contains(ShiftRequest.MISSING_SHIFT)) {
            viewModel.errorProperty().setValue(ShiftRequest.MISSING_SHIFT.getMessage());
        }
    }

    @Override
    public void presentDeleteShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation delete shift");
    }

    @Override
    public void presentDeleteShiftError(Throwable error) {
        viewModel.errorProperty().setValue("IMPOSSIBLE DELETE SHIFT");
        viewModel.setErrorAssignVisibility(true);
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
        if (request.getErrorList().contains(CreateShiftRequest.MISSING_HALL)) {
            viewModel.errorProperty().setValue(CreateShiftRequest.MISSING_HALL.getMessage());
        }
    }

    @Override
    public void presentCreateShiftNullRequest() {
        viewModel.errorProperty().setValue("Error with operation create shift");
    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {
        String error = "";
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_EMPLOYEE)) {
            viewModel.errorProperty().setValue(error +ShiftRepeatRequest.MISSING_EMPLOYEE.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_START.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END)) {
            viewModel.errorProperty().setValue(error+ShiftRepeatRequest.MISSING_END.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_OPTION)) {
            viewModel.errorProperty().setValue(error+ShiftRepeatRequest.MISSING_OPTION.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_START_SHIFT)) {
            viewModel.errorProperty().setValue(error + ShiftRepeatRequest.MISSING_START_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_END_SHIFT)) {
            viewModel.errorProperty().setValue(error+ShiftRepeatRequest.MISSING_END_SHIFT.getMessage());
        }
        if (request.getErrorList().contains(ShiftRepeatRequest.MISSING_HALL)) {
            viewModel.errorProperty().setValue(error+ShiftRepeatRequest.MISSING_HALL.getMessage());
        }viewModel.errorAssignVisibilityProperty().setValue(true);
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
    public void presentInvalidHallListRequest(GetHallListRequest request) {
        if (request.getErrorList().contains(GetHallListRequest.MISSING_HALL)) {
            viewModel.errorProperty().setValue(GetHallListRequest.MISSING_HALL.getMessage());
        }
    }

    @Override
    public void presentHallListNullRequest() {
        viewModel.errorProperty().setValue("Error with operation get Hall list");
    }

    private List<EmployeeShiftWeek> getEmployeeShiftWeekList(GetShiftListResponse response) {
        List<EmployeeShiftWeek> result = new ArrayList<>();
        List<ShiftDto> shiftList = response.getShiftDtoList();
        CinemaDto cinema = response.getCinema();
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        LocalDate firstDayOfWeek = response.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<EmployeeDto> employeeList = shiftList.stream()
                .map(ShiftDto::getEmployee)
                .filter(employee -> employee.getCinema().equals(cinema))
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

        Map<EmployeeDto, List<ShiftDto>> employeeShiftListMap = new HashMap<>();
        for (EmployeeDto employee : employeeList) {
            employeeShiftListMap.put(
                    employee,
                    shiftList.stream()
                            .filter(shift -> shift.getEmployee().equals(employee))
                            .collect(Collectors.toList())
            );
        }
        for (EmployeeDto employee : employeeList) {
            Map<DayOfWeek, DayWeek> dayMap = new EnumMap<>(DayOfWeek.class);
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                dayMap.put(
                        dayOfWeek,
                        new DayWeek(
                                firstDayOfWeek.plusDays((long) dayOfWeek.getValue() - 1),
                                employeeShiftListMap.get(employee).stream()
                                        .filter(shift -> shift.getDate().getDayOfWeek() == dayOfWeek)
                                        .filter(shift -> viewModel.getSelectedWeek().getYear() == shift.getDate().getYear())
                                        .filter(shift -> viewModel.getSelectedWeek().get(temporalField) == shift.getDate().get(temporalField))
                                        .collect(Collectors.toList()),
                                employee
                        ));
            }
            result.add(new EmployeeShiftWeek(
                    employee,
                    dayMap
            ));
        }
        return result;
    }

}
