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
                .filter(employee -> employee.getCinema().getId() == viewModel.getSelectedCinema())
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
                                    && shift.getEmployee().getCinema().getId() == viewModel.getSelectedCinema()
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

    }

    @Override
    public void presentDeleteShift() {

    }

    @Override
    public void presentRepeatShift(ShiftRepeatResponse response) {
        System.out.println(response.getShiftDto());
    }

    @Override
    public void presentCreateShift(CreateShiftResponse response) {
        viewModel.setShiftCreated(response.getShiftDto());
    }

    @Override
    public void presentInvalidSaveShiftListRequest(ShiftRequest request) {

    }

    @Override
    public void presentSaveShiftNullRequest() {

    }

    @Override
    public void presentSaveShiftError(Throwable error) {

    }

    @Override
    public void presentInvalidDeleteShiftListRequest(ShiftRequest request) {

    }

    @Override
    public void presentDeleteShiftNullRequest() {

    }

    @Override
    public void presentDeleteShiftError(Throwable error) {

    }

    @Override
    public void presentSaveRepeatedShiftError(Throwable error) {

    }

    @Override
    public void presentInvalidModifyShiftListRequest(ShiftModifyRequest request) {

    }

    @Override
    public void presentModifyShiftNullRequest() {

    }

    @Override
    public void presentModifyShiftError(Throwable error) {


    }

    @Override
    public void presentInvalidCreateShiftListRequest(CreateShiftRequest request) {

    }

    @Override
    public void presentCreateShiftNullRequest() {

    }

    @Override
    public void presentInvalidRepeatedShiftListRequest(ShiftRepeatRequest request) {

    }

    @Override
    public void presentRepeatedShiftNullRequest() {

    }

    @Override
    public void presentInvalidGetShiftListRequest(GetShiftListRequest request) {

    }

    @Override
    public void presentGetShiftListNullRequest() {

    }

    @Override
    public void presentInvalidHallListRequest(GetHallListRequest request) {

    }

    @Override
    public void presentHallListNullRequest() {

    }


}
