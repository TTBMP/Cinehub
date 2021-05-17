package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.CinemaDto;

public class CinemaResponse {

    private final CinemaDto cinemaDto;

    public CinemaResponse(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;

    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

}
