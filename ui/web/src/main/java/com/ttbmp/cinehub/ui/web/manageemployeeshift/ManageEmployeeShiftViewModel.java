package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ManageEmployeeShiftViewModel {

    private Object selectedShiftDto;
    private List<CinemaDto> cinemaDtoList;
    private int selectedCinema;
    private LocalDate selectedDate;
    private List<ShiftDto> shiftList;
    private Map<EmployeeDto, List<ShiftDto>> employeeShiftMap;
    private List<EmployeeDto> employeeDtoList;
    private List<HallDto> hallDtoList;
    private ShiftDto shiftCreated;
    private ShiftDto shiftSelected;

    public ShiftDto getShiftSelected() {
        return shiftSelected;
    }

    public void setShiftSelected(ShiftDto shiftSelected) {
        this.shiftSelected = shiftSelected;
    }

    public List<HallDto> getHallDtoList() {
        return hallDtoList;
    }

    public void setHallDtoList(List<HallDto> hallDtoList) {
        this.hallDtoList = hallDtoList;
    }

    public ShiftDto getShiftCreated() {
        return shiftCreated;
    }

    public void setShiftCreated(ShiftDto shiftCreated) {
        this.shiftCreated = shiftCreated;
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    public Object getSelectedShiftDto() {
        return selectedShiftDto;
    }

    public void setSelectedShiftDto(Object selectedShiftDto) {
        this.selectedShiftDto = selectedShiftDto;
    }

    public Map<EmployeeDto, List<ShiftDto>> getEmployeeShiftMap() {
        return employeeShiftMap;
    }

    public void setEmployeeShiftMap(Map<EmployeeDto, List<ShiftDto>> employeeShiftMap) {
        this.employeeShiftMap = employeeShiftMap;
    }

    public List<ShiftDto> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftDto> shiftList) {
        this.shiftList = shiftList;
    }

    public int getSelectedCinema() {
        return selectedCinema;
    }

    public void setSelectedCinema(int selectedCinema) {
        this.selectedCinema = selectedCinema;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<CinemaDto> getCinemaDtoList() {
        return cinemaDtoList;
    }

    public void setCinemaDtoList(List<CinemaDto> cinemaDtoList) {
        this.cinemaDtoList = cinemaDtoList;
    }
}
