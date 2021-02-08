package com.ttbmp.cinehub.domain.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.domain.dto.CinemaDto;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class GetCinemaListResponse {
    private List<CinemaDto> cinemaList;

    public GetCinemaListResponse(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }

}
