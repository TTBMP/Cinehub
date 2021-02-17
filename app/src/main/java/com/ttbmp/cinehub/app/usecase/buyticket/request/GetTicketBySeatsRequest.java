package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class GetTicketBySeatsRequest extends Request {


    public static final Request.Error MISSING_LIST_SEATS_ERROR = new Request.Error("List seats can't be null");
    public static final Request.Error MISSING_POSITION_ERROR = new Request.Error("Position can't be null");
    public static final Request.Error MISSING_INDEX_ERROR = new Request.Error("Index can't be null");

    private final List<SeatDto> seatDtoList;
    private final String position;
    private final Integer pos;
    private final Boolean foldingArmchairOption;
    private final Boolean heatedArmchairOption;
    private final Boolean skipLineRadioOption;

    public GetTicketBySeatsRequest(List<SeatDto> seatDtoList,
                                   String position,
                                   Integer pos,
                                   Boolean foldingArmchairOption,
                                   Boolean heatedArmchairOption,
                                   Boolean skipLineRadioOption) {
        this.seatDtoList = seatDtoList;
        this.position = position;
        this.pos = pos;
        this.foldingArmchairOption = foldingArmchairOption;
        this.heatedArmchairOption = heatedArmchairOption;
        this.skipLineRadioOption = skipLineRadioOption;
    }


    public Boolean getFoldingArmchairOption() {
        return foldingArmchairOption;
    }

    public Boolean getHeatedArmchairOption() {
        return heatedArmchairOption;
    }

    public Boolean getSkipLineRadioOption() {
        return skipLineRadioOption;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }


    public String getPosition() {
        return position;
    }


    public Integer getPos() {
        return pos;
    }


    @Override
    public void onValidate() {
        if (seatDtoList == null) {
            addError(MISSING_LIST_SEATS_ERROR);
        }
        if (position == null) {
            addError(MISSING_POSITION_ERROR);
        }
        if (pos == null) {
            addError(MISSING_INDEX_ERROR);
        }
    }
}
