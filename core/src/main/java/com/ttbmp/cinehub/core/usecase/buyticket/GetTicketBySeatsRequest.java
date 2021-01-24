package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.dto.SeatDto;
import com.ttbmp.cinehub.core.usecase.Request;

import java.util.List;

public class GetTicketBySeatsRequest extends Request {


    public static final Request.Error MISSING_LIST_SEATS_ERROR = new Request.Error("List seats can't be null");
    public static final Request.Error MISSING_POSITION_ERROR = new Request.Error("Position can't be null");
    public static final Request.Error MISSING_INDEX_ERROR = new Request.Error("Index can't be null");

    private List<SeatDto> seats;
    private String position;
    private Integer pos;

    public GetTicketBySeatsRequest(List<SeatDto> seats, String position, Integer pos) {
        this.seats = seats;
        this.position = position;
        this.pos = pos;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public void onValidate() {

    }
}
