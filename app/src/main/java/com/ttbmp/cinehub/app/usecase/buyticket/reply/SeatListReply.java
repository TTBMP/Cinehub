package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.SeatDto;
import lombok.Value;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Value
public class SeatListReply {

    List<SeatDto> seatDtoList;

}
