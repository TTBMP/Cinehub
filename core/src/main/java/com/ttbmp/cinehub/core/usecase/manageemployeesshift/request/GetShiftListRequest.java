package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.utilities.notification.Notification;

import java.time.LocalDate;

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
