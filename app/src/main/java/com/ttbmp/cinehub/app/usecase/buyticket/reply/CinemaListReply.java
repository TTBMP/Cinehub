package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.CinemaDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class CinemaListReply {

    private final List<CinemaDto> cinemaList;

    public CinemaListReply(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;

    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

}
