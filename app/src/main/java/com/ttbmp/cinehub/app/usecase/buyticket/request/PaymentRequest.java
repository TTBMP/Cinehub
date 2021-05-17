package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class PaymentRequest extends Request {

    public static final Request.Error MISSING_TICKET_ERROR = new Request.Error("Ticket can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    public static final Request.Error MISSING_EMAIL_ERROR = new Request.Error("Email can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_CREDIT_CARD_ERROR = new Request.Error("Credit card can't be null");

    private String email;
    private TicketDto ticketDto;
    private String creditCardNumber;
    private String creditCardCvv;
    private String creditCardExpirationDate;

    public PaymentRequest(TicketDto ticketDto, String creditCardNumber, String creditCardCvv, String creditCardExpirationDate, String email) {
        this.email = email;
        this.ticketDto = ticketDto;
        this.creditCardNumber = creditCardNumber;
        this.creditCardCvv = creditCardCvv;
        this.creditCardExpirationDate = creditCardExpirationDate;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardCvv() {
        return creditCardCvv;
    }

    public void setCreditCardCvv(String creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    public String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(String creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    @Override
    public void onValidate() {
        if (email == null) {
            addError(MISSING_EMAIL_ERROR);
        }
        if (ticketDto == null) {
            addError(MISSING_TICKET_ERROR);
        }
        if (creditCardNumber == null) {
            addError(MISSING_CREDIT_CARD_ERROR);
        }
    }

}
