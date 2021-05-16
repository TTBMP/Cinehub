package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class TicketRequest extends Request {

    public static final Request.Error MISSING_LIST_SEATS_ERROR = new Request.Error("List seats can't be null");
    public static final Request.Error MISSING_OPTION_ONE_ERROR = new Request.Error("Option one can't be null");
    public static final Request.Error MISSING_OPTION_TWO_ERROR = new Request.Error("Option two can't be null");
    public static final Request.Error MISSING_OPTION_THREE_ERROR = new Request.Error("Option three can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection id can't be NEGATIVE");
    public static final Request.Error MISSING_NUMBER_ERROR = new Request.Error("Number can't be null");

    private final List<SeatDto> seatDtoList;
    private final Integer position;
    private final Boolean openBarOption;
    private final Boolean magicBoxOption;
    private final Boolean skipLineOption;
    private final int projectionId;


    public TicketRequest(List<SeatDto> seatDtoList,
                         Integer position,
                         Boolean magicBoxOption,
                         Boolean openBarOption,
                         Boolean skipLineOption,
                         int projectionId) {
        this.seatDtoList = seatDtoList;
        this.position = position;
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


    public Integer getPosition() {
        return position;
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
            addError(MISSING_NUMBER_ERROR);
        }
        if (magicBoxOption == null) {
            addError(MISSING_OPTION_ONE_ERROR);
        }
        if (openBarOption == null) {
            addError(MISSING_OPTION_TWO_ERROR);
        }
        if (skipLineOption == null) {
            addError(MISSING_OPTION_THREE_ERROR);
        }
        if (projectionId < 0) {
            addError(MISSING_PROJECTION_ERROR);
        }
    }
}
