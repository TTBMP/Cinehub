package com.ttbmp.cinehub.app.usecase.buyticket.request;


import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class GetProjectionRequest extends Request {

    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Movie can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");


    private Integer movieDtoId;
    private Integer cinemaDtoId;
    private String localDate;
    private String startTime;
    private Integer hallId;

    public GetProjectionRequest(Integer movieDtoId, Integer cinemaDtoId, LocalDate localDate,String startTime,Integer hallId) {
        this.movieDtoId = movieDtoId;
        this.cinemaDtoId = cinemaDtoId;
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

    public Integer getMovieDtoId() {
        return movieDtoId;
    }

    public void setMovieDtoId(Integer movieDtoId) {
        this.movieDtoId = movieDtoId;
    }

    public Integer getCinemaDtoId() {
        return cinemaDtoId;
    }

    public void setCinemaDtoId(Integer cinemaDtoId) {
        this.cinemaDtoId = cinemaDtoId;
    }

    @Override
    public void onValidate() {
        if (movieDtoId == null) {
            addError(MISSING_MOVIE_ERROR);
        }
        if (localDate == null) {
            addError(MISSING_DATE_ERROR);
        }

    }
}
