package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockViewPersonalScheduleViewModel {

    private String sessionToken;
    private List<ShiftDto> shiftList;
    private List<ProjectionDto> projectionList;

    private String errorMessage;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public List<ShiftDto> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftDto> shiftList) {
        this.shiftList = shiftList;
    }

    public List<ProjectionDto> getProjectionList() {
        return projectionList;
    }

    public void setProjectionList(List<ProjectionDto> projectionList) {
        this.projectionList = projectionList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
