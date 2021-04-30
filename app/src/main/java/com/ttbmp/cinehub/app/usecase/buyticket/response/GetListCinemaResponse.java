package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.CinemaDto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class GetListCinemaResponse {

    private final List<CinemaDto> cinemaList;

    public GetListCinemaResponse(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;

    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

}
