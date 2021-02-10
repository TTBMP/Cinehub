package com.ttbmp.cinehub.app.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Massimo Mazzetti
 */
public class GetShiftListResponse {
    private List<ShiftDto> shiftDtoList;
    private LocalDate date;
    //private CinemaDto cinema;
    private int cinema;

    public GetShiftListResponse(List<ShiftDto> shiftList, LocalDate date, int cinema) {
        this.shiftDtoList = shiftList;
        this.date = date;
        this.cinema = cinema;
    }

    public List<ShiftDto> getShiftDtoList() {
        return shiftDtoList;
    }

    public void setShiftDtoList(List<ShiftDto> shiftDtoList) {
        this.shiftDtoList = shiftDtoList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCinema() {
        return cinema;
    }

    public void setCinema(int cinema) {
        this.cinema = cinema;
    }

    /*    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }*/

}
