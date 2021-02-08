package com.ttbmp.cinehub.app.dto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class HallDto {

    private Integer id;
    private List<SeatDto> seatList;

    public HallDto(Integer id) {
        this.id = id;
    }

    public HallDto(Integer id, List<SeatDto> seatList) {
        this.id = id;
        this.seatList = seatList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SeatDto> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<SeatDto> seatList) {
        this.seatList = seatList;
    }

}
