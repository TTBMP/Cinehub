package com.ttbmp.cinehub.core.entity;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class Hall {

    private Integer id;
    private List<Seat> seatList;


    public Hall(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}
