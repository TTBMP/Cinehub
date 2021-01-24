package com.ttbmp.cinehub.core.entity;

import java.util.List;

public class Hall {

    private Integer id;
    private List<Projection> projectionList;
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

    public List<Projection> getProjectionList() {
        return projectionList;
    }

    public void setProjectionList(List<Projection> projectionList) {
        this.projectionList = projectionList;
    }

    public void addProjection(Projection projection) {
        this.projectionList.add(projection);
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}
