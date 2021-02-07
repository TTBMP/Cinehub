package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;


import com.ttbmp.cinehub.core.dto.HallDto;

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
