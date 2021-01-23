package com.ttbmp.cinehub.core.dto;

/**
 * @author Massimo Mazzetti
 */

public class HallDto {

    private String num;
    private CinemaDto cinema;

    public HallDto() {

    }

    public HallDto(String num, CinemaDto cinema) {
        this.num = num;
        this.cinema = cinema;
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
