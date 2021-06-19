package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
@Value
public class GetShiftListReply {

    List<ShiftDto> shiftDtoList;
    LocalDate date;
    int cinemaId;

}
