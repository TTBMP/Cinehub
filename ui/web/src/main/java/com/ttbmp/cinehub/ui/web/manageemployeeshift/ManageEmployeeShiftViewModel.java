package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ManageEmployeeShiftViewModel {

    private ShiftDto shiftDto;
    private List<CinemaDto> cinemaDtoList;
    private int selectedCinema;
    private LocalDate selectedDate;
    private List<ShiftDto> shiftList;
    private Map<EmployeeDto, List<Object>> employeeShiftMap;
    private List<EmployeeDto> employeeDtoList;

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    public Map<EmployeeDto, List<Object>> getEmployeeShiftMap() {
        return employeeShiftMap;
    }

    public void setEmployeeShiftMap(Map<EmployeeDto, List<Object>> employeeShiftMap) {
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
