package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ManageEmployeeShiftViewModel {


    private List<CinemaDto> cinemaDtoList;
    private CinemaDto selectedCinema;
    private LocalDate selectedDate;
    private Map<EmployeeDto, List<ShiftDto>> employeeShiftMap;
    private List<EmployeeDto> employeeDtoList;
    private List<HallDto> hallDtoList;
    private ShiftDto shiftCreated;
    private ShiftDto shiftSelected;

    private boolean assignError;
    private boolean deleteError;
    private boolean modifyError;
    private String assignErrorText;
    private String deleteErrorText;
    private String modifyErrorText;

    public boolean isModifyError() {
        return modifyError;
    }

    public void setModifyError(boolean modifyError) {
        this.modifyError = modifyError;
    }

    public String getModifyErrorText() {
        return modifyErrorText;
    }

    public void setModifyErrorText(String modifyErrorText) {
        this.modifyErrorText = modifyErrorText;
    }

    public String getAssignErrorText() {
        return assignErrorText;
    }

    public void setAssignErrorText(String assignErrorText) {
        this.assignErrorText = assignErrorText;
    }

    public String getDeleteErrorText() {
        return deleteErrorText;
    }

    public void setDeleteErrorText(String deleteErrorText) {
        this.deleteErrorText = deleteErrorText;
    }

    public boolean isAssignError() {
        return assignError;
    }

    public void setAssignError(boolean assignError) {
        this.assignError = assignError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }

    public void setDeleteError(boolean deleteError) {
        this.deleteError = deleteError;
    }

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

    public Map<EmployeeDto, List<ShiftDto>> getEmployeeShiftMap() {
        return employeeShiftMap;
    }

    public void setEmployeeShiftMap(Map<EmployeeDto, List<ShiftDto>> employeeShiftMap) {
        this.employeeShiftMap = employeeShiftMap;
    }

    public CinemaDto getSelectedCinema() {
        return selectedCinema;
    }

    public void setSelectedCinema(CinemaDto selectedCinema) {
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
