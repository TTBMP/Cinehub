package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.SeatDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class NumberOfSeatsResponse {


    private final List<SeatDto> seatDtoList;

    public NumberOfSeatsResponse(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

}
