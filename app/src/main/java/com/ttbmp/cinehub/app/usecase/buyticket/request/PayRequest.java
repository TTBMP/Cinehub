package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.Request;

/**
 * @author Palmieri Ivan
 */
public class PayRequest extends Request {
    public static final Request.Error MISSING_TICKET_ERROR = new Request.Error("Ticket can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    public static final Request.Error MISSING_INDEX_ERROR = new Request.Error("Index can't be after the end time");

    private final Integer index;
    private ProjectionDto projection;
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
        if (index == null) {
            addError(MISSING_INDEX_ERROR);
        }
        if (projection == null) {
            addError(MISSING_PROJECTION_ERROR);

        }
        if (ticket == null) {
            addError(MISSING_TICKET_ERROR);
        }
    }
}
