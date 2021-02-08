package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.SeatDto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class GetNumberOfSeatsResponse {


    private final List<SeatDto> seatDtoList;

    public GetNumberOfSeatsResponse(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

}
