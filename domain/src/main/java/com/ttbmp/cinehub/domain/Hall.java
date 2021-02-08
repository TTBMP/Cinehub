package com.ttbmp.cinehub.domain;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class Hall {

    private Integer id;
    private List<Seat> seatList;


    public Hall(Integer id, List<Seat> seatList) {
        this.id = id;
        this.seatList = seatList;
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Hall elem = (Hall) obj;

        return this.getId().equals(elem.getId())
                && this.getSeatList().equals(elem.getSeatList());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
