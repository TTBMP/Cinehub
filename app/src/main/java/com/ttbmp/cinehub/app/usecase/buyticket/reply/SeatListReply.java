package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.SeatDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class SeatListReply {

    private final List<SeatDto> seatDtoList;

    public SeatListReply(List<SeatDto> seatDtoList) {
        this.seatDtoList = seatDtoList;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

}
