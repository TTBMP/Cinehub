package com.ttbmp.cinehub.core.entity;
/**
 * @author Palmieri Ivan
 */

import java.util.List;

public class Hall {

    private Integer id;

    private List<Seat> seatList;

    private List<Projection> projectionList;

    public void setProjectionList(List<Projection> projectionList) {
        this.projectionList = projectionList;
    }

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

    public List<Projection> getProjectionList() {
        return projectionList;
    }
}
