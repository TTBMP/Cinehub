package com.ttbmp.cinehub.domain;

import java.util.List;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class Hall {

    private int id;
    private List<Seat> seatList;
    private String name;

    public Hall(int id, List<Seat> seatList, String name) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (Hall) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

}
