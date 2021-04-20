package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.time.LocalDate;

public class GetCinemaRequest {

    private int cinemaId;
    private LocalDate start;

    public GetCinemaRequest() {
    }

    public GetCinemaRequest(int cinemaId, LocalDate start) {
        this.cinemaId = cinemaId;
        this.start = start;
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
