package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.entity.Cinema;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */

public class GetCinemaListResponse {
    private List<CinemaDto> cinemaList;

    public GetCinemaListResponse(List<Cinema> cinemaList) {
        this.cinemaList = CinemaDataMapper.mapToDtoList(cinemaList);
    }

    public List<CinemaDto> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<CinemaDto> cinemaList) {
        this.cinemaList = cinemaList;
    }
}
