package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import lombok.Value;

import java.util.List;

@Value
public class ShiftRepeatReply {

    List<ShiftDto> shiftDto;

}
