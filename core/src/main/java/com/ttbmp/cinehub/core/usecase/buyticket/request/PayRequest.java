package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.ProjectionDto;
import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class PayRequest extends Request {
    public static final Request.Error MISSING_TICKET_ERROR = new Request.Error("Ticket can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    public static final Request.Error MISSING_INDEX_ERROR = new Request.Error("Index can't be after the end time");
    private ProjectionDto projection;
    private final Integer index;
    private TicketDto ticket;

    public PayRequest(TicketDto ticket, ProjectionDto projection, Integer index) {
        this.index = index;
        this.projection = projection;
        this.ticket = ticket;
    }

    public ProjectionDto getProjection() {
        return projection;
    }

    public void setProjection(ProjectionDto projection) {
        this.projection = projection;
    }

    public Integer getIndex() {
        return index;
    }


    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }

    @Override
    public void onValidate() {

    }
}
