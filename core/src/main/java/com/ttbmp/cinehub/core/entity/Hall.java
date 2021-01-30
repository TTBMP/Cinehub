package com.ttbmp.cinehub.core.entity;

/**
 * @author Massimo Mazzetti
 */

public class Hall {
    private String num;
    private Cinema cinema;

    public Hall() {

    }

    public Hall(String num, Cinema cinema) {
        this.num = num;
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

        return this.getNum().equals(elem.getNum()) &&
                this.getCinema().equals(elem.getCinema());
    }

    @Override
    public int hashCode() {

        return 0;
    }
}
