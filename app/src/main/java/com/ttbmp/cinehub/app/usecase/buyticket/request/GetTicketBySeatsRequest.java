package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.usecase.Request;

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
    private final Integer number;
    private final Boolean magicBoxOption;
    private final Boolean openBarOption;
    private final Boolean skipLineOption;
    private final int projectionId;

    public GetTicketBySeatsRequest(List<SeatDto> seatDtoList,
                                   String position,
                                   Integer number,
                                   Boolean magicBoxOption,
                                   Boolean openBarOption,
                                   Boolean skipLineOption,
                                   int projectionId) {
        this.seatDtoList = seatDtoList;
        this.position = position;
        this.number = number;
        this.magicBoxOption = magicBoxOption;
        this.openBarOption = openBarOption;
        this.skipLineOption = skipLineOption;
        this.projectionId = projectionId;
    }

    public Boolean getMagicBoxOption() {
        return magicBoxOption;
    }

    public Boolean getOpenBarOption() {
        return openBarOption;
    }

    public Boolean getSkipLineOption() {
        return skipLineOption;
    }

    public List<SeatDto> getSeatDtoList() {
        return seatDtoList;
    }

    public String getPosition() {
        return position;
    }

    public Integer getNumber() {
        return number;
    }

    public int getProjectionId() {
        return projectionId;
    }

    @Override
    public void onValidate() {
        if (seatDtoList == null) {
            addError(MISSING_LIST_SEATS_ERROR);
        }
        if (position == null) {
            addError(MISSING_POSITION_ERROR);
        }
        if (number == null) {
            addError(MISSING_INDEX_ERROR);
        }
    }
}
