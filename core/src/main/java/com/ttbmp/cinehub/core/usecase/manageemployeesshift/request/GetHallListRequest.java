package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.usecase.Request;

public class GetHallListRequest extends Request {

    private Cinema cinema;

    public GetHallListRequest(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void validate() {

    }
}
