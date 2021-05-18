package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class TicketRequest extends Request {

    public static final Request.Error MISSING_OPTION_ONE_ERROR = new Request.Error("Option one can't be null");
    public static final Request.Error MISSING_OPTION_TWO_ERROR = new Request.Error("Option two can't be null");
    public static final Request.Error MISSING_OPTION_THREE_ERROR = new Request.Error("Option three can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection id can't be NEGATIVE");

    private int seatId;
    private Boolean openBarOption;
    private Boolean magicBoxOption;
    private Boolean skipLineOption;
    private int projectionId;


    public TicketRequest(int seatId,
                         Boolean magicBoxOption,
                         Boolean openBarOption,
                         Boolean skipLineOption,
                         int projectionId) {
        this.seatId = seatId;
        this.magicBoxOption = magicBoxOption;
        this.openBarOption = openBarOption;
        this.skipLineOption = skipLineOption;
        this.projectionId = projectionId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Boolean getOpenBarOption() {
        return openBarOption;
    }

    public void setOpenBarOption(Boolean openBarOption) {
        this.openBarOption = openBarOption;
    }

    public Boolean getMagicBoxOption() {
        return magicBoxOption;
    }

    public void setMagicBoxOption(Boolean magicBoxOption) {
        this.magicBoxOption = magicBoxOption;
    }

    public Boolean getSkipLineOption() {
        return skipLineOption;
    }

    public void setSkipLineOption(Boolean skipLineOption) {
        this.skipLineOption = skipLineOption;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    @Override
    public void onValidate() {
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
