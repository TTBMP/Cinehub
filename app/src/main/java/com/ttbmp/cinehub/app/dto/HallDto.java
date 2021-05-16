package com.ttbmp.cinehub.app.dto;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class HallDto {

    private Integer id;
    private List<SeatDto> seatList;
    private String name;

    public HallDto(Integer id) {
        this.id = id;
    }

    public HallDto(Integer id, List<SeatDto> seatList, String name) {
        this.id = id;
        this.seatList = seatList;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
