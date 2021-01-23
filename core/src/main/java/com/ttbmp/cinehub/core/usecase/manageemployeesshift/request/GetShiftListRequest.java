package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Cinema;

import java.time.LocalDate;

/**
 * @author Massimo Mazzetti
 */

public class GetShiftListRequest {

    private Cinema cinema;
    private LocalDate start;


    public GetShiftListRequest(LocalDate start, Cinema cinema) {
        this.start = start;
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }
}
