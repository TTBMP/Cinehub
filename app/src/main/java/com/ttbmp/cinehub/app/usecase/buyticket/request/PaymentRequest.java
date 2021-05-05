package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.Request;


/**
 * @author Ivan Palmieri
 */
public class PaymentRequest extends Request {

    public static final Request.Error MISSING_TICKET_ERROR = new Request.Error("Ticket can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    public static final Request.Error MISSING_EMAIL_ERROR = new Request.Error("Email can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_CREDIT_CARD_ERROR = new Request.Error("Credit card can't be null");

    private ProjectionDto projection;
    private String email;
    private TicketDto ticketDto;
    private CinemaDto cinemaDto;
    private CreditCardDto creditCard;

    public PaymentRequest(TicketDto ticketDto, ProjectionDto projection,  CinemaDto cinemaDto,   CreditCardDto creditCard, String email) {
        this.projection = projection;
        this.ticketDto = ticketDto;
        this.cinemaDto = cinemaDto;
        this.creditCard = creditCard;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    public ProjectionDto getProjection() {
        return projection;
    }

    public void setProjection(ProjectionDto projection) {
        this.projection = projection;
    }


    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void onValidate() {

        if (projection == null) {
            addError(MISSING_PROJECTION_ERROR);
        }
        if (ticketDto == null) {
            addError(MISSING_TICKET_ERROR);

        }
        if (cinemaDto == null) {
            addError(MISSING_CINEMA_ERROR);
        }
        if (creditCard == null) {
            addError(MISSING_CREDIT_CARD_ERROR);

        }
        if (email == null) {
            addError(MISSING_EMAIL_ERROR);
        }

    }
}
