package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.dto.CinemaDto;

import java.util.List;

public class GetListCinemaResponse {

    private List<CinemaDto> cinemaList;

    public GetListCinemaResponse(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;

    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }
}
