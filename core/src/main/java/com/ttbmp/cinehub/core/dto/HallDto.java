package com.ttbmp.cinehub.core.dto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class HallDto {

    private Integer id;
    private List<ProjectionDto> projectionList;
    private List<SeatDto> seatList;

    public HallDto(Integer id) {
        this.id = id;
    }


    public Integer idProperty() {
        return id;
    }


    public List<ProjectionDto> getProjectionList() {
        return projectionList;
    }


    public List<SeatDto> getSeatList() {
        return seatList;
    }


}
