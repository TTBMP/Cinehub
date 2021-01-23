package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.DayWeek;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table.EmployeeShiftWeek;
import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftPresenter;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetHallListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.ShiftResponse;
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
        viewModel.getEmployeeShiftWeekList().addAll(getEmployeeShiftWeekList(ShiftDataMapper.mapToEntityList(shiftList.getValue().getShiftDtoList()),
                shiftList.getValue().getDate(),
                CinemaDataMapper.matToEntity(shiftList.getValue().getCinema())));
    }

    @Override
    public void presentCinemaList(Result<GetCinemaListResponse> listCinema) {
        viewModel.getCinemaList().setAll(listCinema.getValue().getCinemaList());
    }

    @Override
    public void presentHallList(Result<GetHallListResponse> listHall) {
        viewModel.getSalaList().setAll(listHall.getValue().getListHall());
    }


    @Override
    public void presentSaveShift(Result<ShiftResponse> shift) {
        Shift savedShift = ShiftDataMapper.mapToEntity(shift.getValue().getShiftDto());
        viewModel.getEmployeeShiftWeekList().setAll(viewModel.getEmployeeShiftWeekList().stream()
                .peek(e -> {
                    if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(savedShift.getEmployee())) {
                        switch (LocalDate.parse(savedShift.getDate()).getDayOfWeek()) {
                            case MONDAY:
                                e.getMonday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case TUESDAY:
                                e.getTuesday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case WEDNESDAY:
                                e.getWednesday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case THURSDAY:
                                e.getThursday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case FRIDAY:
                                e.getFriday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case SATURDAY:
                                e.getSaturday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                            case SUNDAY:
                                e.getSunday().getShiftList().add(ShiftDataMapper.mapToDto(savedShift));
                                break;
                        }
                    }
                })
                .collect(Collectors.toList())
        );
    }


    @Override
    public void presentDeleteShift(Result<ShiftResponse> shift) {
        Shift deleteShift = ShiftDataMapper.mapToEntity(shift.getValue().getShiftDto());
        viewModel.getEmployeeShiftWeekList().setAll(viewModel.getEmployeeShiftWeekList().stream()
                .peek(e -> {
                    if (EmployeeDataMapper.matToEntity(e.getEmployeeDto()).equals(deleteShift.getEmployee())) {
                        switch (LocalDate.parse(deleteShift.getDate()).getDayOfWeek()) {
                            case MONDAY:
                                e.getMonday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case TUESDAY:
                                e.getTuesday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case WEDNESDAY:
                                e.getWednesday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case THURSDAY:
                                e.getThursday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case FRIDAY:
                                e.getFriday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case SATURDAY:
                                e.getSaturday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                            case SUNDAY:
                                e.getSunday().getShiftList().removeIf(s -> ShiftDataMapper.mapToEntity(s).equals(deleteShift));
                                break;
                        }
                    }
                })
                .collect(Collectors.toList())
        );
    }


    private List<EmployeeShiftWeek> getEmployeeShiftWeekList(List<Shift> shiftList, LocalDate date, Cinema cinema) {
        List<EmployeeShiftWeek> result = new ArrayList<>();
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        LocalDate firstDayOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<Employee> employeeList = shiftList.stream()
                .map(Shift::getEmployee)
                .filter(employee -> employee.getCinema().equals(cinema))
                .distinct()
                .collect(Collectors.toList());

        List<Employee> tmp = new ArrayList<>();
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

        Map<Employee, List<Shift>> employeeShiftListMap = new HashMap<>();
        for (Employee employee : employeeList) {
            employeeShiftListMap.put(
                    employee,
                    shiftList.stream()
                            .filter(shift -> shift.getEmployee().equals(employee))
                            .collect(Collectors.toList())
            );
        }
        for (Employee employee : employeeList) {
            Map<DayOfWeek, DayWeek> dayMap = new EnumMap<>(DayOfWeek.class);
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                dayMap.put(
                        dayOfWeek,
                        new DayWeek(
                                firstDayOfWeek.plusDays((long) dayOfWeek.getValue() - 1),
                                ShiftDataMapper.mapToDtoList(
                                        employeeShiftListMap.get(employee).stream()
                                                .filter(shift -> LocalDate.parse(shift.getDate()).getDayOfWeek() == dayOfWeek)
                                                .filter(shift -> viewModel.getSelectedWeek().getYear() == LocalDate.parse(shift.getDate()).getYear())
                                                .filter(shift -> viewModel.getSelectedWeek().get(temporalField) == LocalDate.parse(shift.getDate()).get(temporalField))
                                                .collect(Collectors.toList())),
                                EmployeeDataMapper.mapToDto(employee)
                        ));
            }
            result.add(new EmployeeShiftWeek(
                    EmployeeDataMapper.mapToDto(employee),
                    dayMap.get(DayOfWeek.MONDAY),
                    dayMap.get(DayOfWeek.TUESDAY),
                    dayMap.get(DayOfWeek.WEDNESDAY),
                    dayMap.get(DayOfWeek.THURSDAY),
                    dayMap.get(DayOfWeek.FRIDAY),
                    dayMap.get(DayOfWeek.SATURDAY),
                    dayMap.get(DayOfWeek.SUNDAY)
            ));
        }
        return result;
    }

}
