package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.CinemaDto;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class GetCinemaListReply {
    private List<CinemaDto> cinemaList;

    public GetCinemaListReply(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }

}
