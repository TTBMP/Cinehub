package com.ttbmp.cinehub.app.service.payment;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class PayServiceRequest extends Request {

    public static final Request.Error MISSING_USER_DATE_ERROR = new Request.Error("User can't be null");
    public static final Request.Error NEGATIVE_PRICE_ERROR = new Request.Error("TicketPrice can't be < 0");

    private final String email;
    private final String nameUser;
    private final String numberOfCard;
    private final long ticketPrice;

    public PayServiceRequest(String email, String nameUser, String numberOfCard, long ticketPrice) {
        this.email = email;
        this.nameUser = nameUser;
        this.numberOfCard = numberOfCard;
        this.ticketPrice = ticketPrice;
    }

    public String getEmail() {
        return email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getNumberOfCard() {
        return numberOfCard;
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void onValidate() {
        if (email == null || numberOfCard == null || nameUser == null) {
            addError(MISSING_USER_DATE_ERROR);
        }
        if (ticketPrice < 0) {
            addError(NEGATIVE_PRICE_ERROR);
        }
    }
}
