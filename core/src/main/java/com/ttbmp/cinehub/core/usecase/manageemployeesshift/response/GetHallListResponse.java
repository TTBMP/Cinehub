package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.datamapper.HallDataMapper;
import com.ttbmp.cinehub.core.dto.HallDto;
import com.ttbmp.cinehub.core.entity.Hall;

import java.util.List;

public class GetHallListResponse {
    List<HallDto> listHall;

    public GetHallListResponse(List<Hall> listHall) {
        this.listHall = HallDataMapper.mapToDtoList(listHall);
    }

    public List<HallDto> getListHall() {
        return listHall;
    }

    public void setListHall(List<HallDto> listHall) {
        this.listHall = listHall;
    }
}
