package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import lombok.Value;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
@Value
public class GetCinemaListReply {

    List<CinemaDto> cinemaList;

}
