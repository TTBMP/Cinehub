package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import java.time.LocalDate;

public class GetCinemaForm {

    private int cinemaId;
    private LocalDate start;

    public GetCinemaForm() {
    }

    public GetCinemaForm(int cinemaId, LocalDate start) {
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
