package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import lombok.Value;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Value
public class CinemaListReply {

    List<CinemaDto> cinemaList;

}
