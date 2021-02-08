package com.ttbmp.cinehub.domain.usecase.manageemployeesshift.response;


import com.ttbmp.cinehub.domain.dto.HallDto;

import java.util.List;


/**
 * @author Massimo Mazzetti
 */
public class GetHallListResponse {

    List<HallDto> listHall;

    public GetHallListResponse(List<HallDto> listHall) {
        this.listHall = listHall;
    }

    public List<HallDto> getListHall() {
        return listHall;
    }

    public void setListHall(List<HallDto> listHall) {
        this.listHall = listHall;
    }

}
