package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class PaymentRequest extends Request {

    public static final Request.Error MISSING_EMAIL_ERROR = new Request.Error("Email can't be null");
    public static final Request.Error MISSING_CINEMA_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_CREDIT_CARD_ERROR = new Request.Error("Credit card can't be null");

    private int projectionId;
    private int seatId;
    private long ticketPrice;
    private String email;
    private String creditCardNumber;
    private String creditCardCvv;
    private String creditCardExpirationDate;

    public PaymentRequest(int projectionId, int seatId, long ticketPrice, String email, String creditCardNumber, String creditCardCvv, String creditCardExpirationDate) {
        this.projectionId = projectionId;
        this.seatId = seatId;
        this.ticketPrice = ticketPrice;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.creditCardCvv = creditCardCvv;
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (creditCardNumber == null) {
            addError(MISSING_CREDIT_CARD_ERROR);
        }
    }

}
