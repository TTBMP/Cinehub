package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.CinemaDto;

import java.util.List;

public class GetCinemaResponse {


    private final CinemaDto cinemaDto;

    public GetCinemaResponse(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;

    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

}
