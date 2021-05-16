package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class ProjectionRequest extends Request {

    public static final Request.Error MISSING_HALL_ERROR = new Request.Error("Hall can't be null");
    public static final Request.Error MISSING_TIME_ERROR = new Request.Error("Start time can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");

    private String localDate;
    private String startTime;
    private Integer hallId;

    public ProjectionRequest(LocalDate localDate, String startTime, Integer hallId) {

        this.localDate = localDate.toString();
        this.startTime = startTime;
        this.hallId = hallId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }


    @Override
    public void onValidate() {

        if (localDate == null) {
            addError(MISSING_DATE_ERROR);
        }
        if (startTime == null) {
            addError(MISSING_TIME_ERROR);
        }
        if (hallId < 0) {
            addError(MISSING_HALL_ERROR);
        }

    }
}
