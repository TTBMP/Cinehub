package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.SeatDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class SeatListResponse {

    private final List<SeatDto> seatDtoList;

    public SeatListResponse(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

}
