package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.DayWeek;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.core.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.core.utilities.result.Result;

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
    public void presentShiftList(Result<GetShiftListResponse> shiftList) {
        viewModel.getEmployeeShiftWeekList().clear();
        viewModel.getEmployeeShiftWeekList().addAll(getEmployeeShiftWeekList(new GetShiftListResponse(shiftList.getValue().getShiftDtoList(),
                shiftList.getValue().getDate(),
                shiftList.getValue().getCinema())));
    }

    @Override
    public void presentCinemaList(Result<GetCinemaListResponse> listCinema) {
        viewModel.getCinemaList().setAll(listCinema.getValue().getCinemaList());
    }

    @Override
    public void presentHallList(Result<GetHallListResponse> listHall) {
        viewModel.getHallList().setAll(listHall.getValue().getListHall());
    }


    @Override
    public void presentSaveShift(Result<ShiftResponse> shift) {
        Shift savedShift = ShiftDataMapper.mapToEntity(shift.getValue().getShiftDto());
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        viewModel.getEmployeeShiftWeekList().setAll(viewModel.getEmployeeShiftWeekList().stream()
                .peek(e -> {
                    if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(savedShift.getEmployee())
                            &&
                            LocalDate.parse(savedShift.getDate()).get(temporalField) == viewModel.getSelectedWeek().get(temporalField) &&
                            LocalDate.parse(savedShift.getDate()).getYear() == viewModel.getSelectedWeek().getYear()) {
                        e.getWeekMap().get(LocalDate.parse(savedShift.getDate()).getDayOfWeek())
                                .getShiftList()
                                .add(ShiftDataMapper.mapToDto(savedShift));
                    }
                })
                .collect(Collectors.toList()));
    }

    @Override
    public void presentDeleteShift(Result<ShiftResponse> shift) {

        Shift deleteShift = ShiftDataMapper.mapToEntity(shift.getValue().getShiftDto());
        viewModel.getEmployeeShiftWeekList().setAll(viewModel.getEmployeeShiftWeekList().stream()
                .peek(e -> {
                    if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(deleteShift.getEmployee())) {
                        e.getWeekMap().get(LocalDate.parse(deleteShift.getDate()).getDayOfWeek())
                                .getShiftList()
                                .removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                    }
                })
                .collect(Collectors.toList())
        );
    }

    @Override
    public void presentRepeatShift(Result<ShiftRepeatResponse> response) {
        List<ShiftDto> shiftList = response.getValue().getShiftDto();
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        for (ShiftDto savedShift : shiftList) {
            viewModel.getEmployeeShiftWeekList().setAll(viewModel.getEmployeeShiftWeekList().stream()
                    .peek(employeeShiftWeek -> {
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
                    })
                    .collect(Collectors.toList())
            );
        }
    }

    @Override
    public void presentCreateShift(Result<CreateShiftResponse> response) {
        viewModel.setShiftCreated(response.getValue().getShiftDto());
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
