package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class GetShiftListReply {
    private List<ShiftDto> shiftDtoList;
    private LocalDate date;
    private int cinemaId;

    public GetShiftListReply(List<ShiftDto> shiftList, LocalDate date, int cinema) {
        this.shiftDtoList = shiftList;
        this.date = date;
        this.cinemaId = cinema;
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

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
