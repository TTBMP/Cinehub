package com.ttbmp.cinehub.core.usecase.buyticket.response;

import com.ttbmp.cinehub.core.dto.SeatDto;

import java.util.List;

public class GetNumberOfSeatsResponse {


    List<SeatDto> seatDtoList;

    public GetNumberOfSeatsResponse(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

    public void setSeatDtoList(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }
}
