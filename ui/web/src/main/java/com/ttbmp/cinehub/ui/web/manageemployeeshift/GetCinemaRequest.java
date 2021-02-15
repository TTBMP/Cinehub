package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import java.time.LocalDate;

public class GetCinemaRequest {

    private int cinemaId;
    private LocalDate start;

    public GetCinemaRequest() {
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }
}
