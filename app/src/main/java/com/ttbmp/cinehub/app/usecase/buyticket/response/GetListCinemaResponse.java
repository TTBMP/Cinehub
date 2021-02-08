package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.CinemaDto;

import java.util.List;

/**
 * @author Palmieri Ivan
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
