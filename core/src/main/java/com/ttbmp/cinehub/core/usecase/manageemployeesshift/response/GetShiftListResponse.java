package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Shift;

import java.time.LocalDate;
import java.util.List;

public class GetShiftListResponse {
    private List<ShiftDto> shiftDtoList;
    private LocalDate date;
    private CinemaDto cinema;

    public GetShiftListResponse(List<Shift> shiftList, LocalDate date, Cinema cinema ) {
        this.shiftDtoList = ShiftDataMapper.mapToDtoList(shiftList);
        this.date=date;
        this.cinema= CinemaDataMapper.mapToDto(cinema);
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

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }
}
