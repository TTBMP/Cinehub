package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class PaymentRequest extends AuthenticatedRequest {

    public static final Request.Error LENGTH_CVV_CREDIT_CARD_ERROR = new Request.Error("The CVV must be three numbers in length");
    public static final Request.Error EXPIRATION_CREDIT_CARD_ERROR = new Request.Error("You cannot select a date in the past");
    public static final Request.Error EMAIL_ERROR = new Request.Error("The email is invalid");
    public static final Request.Error CREDIT_CARD_LENGTH_ERROR = new Request.Error("The credit card must have a length of 16 characters");
    public static final Request.Error CVV_LETTERS_ERROR = new Request.Error("The CVV cannot contain letters");
    public static final Request.Error NUMBER_OF_CARD_LETTERS_ERROR = new Request.Error("The number of card cannot contain letters");
    public static final Request.Error MISSING_OPTION_ONE_ERROR = new Request.Error("Option one can't be null");
    public static final Request.Error MISSING_OPTION_TWO_ERROR = new Request.Error("Option two can't be null");
    public static final Request.Error MISSING_OPTION_THREE_ERROR = new Request.Error("Option three can't be null");
    public static final Request.Error MISSING_CUSTOMER_ERROR = new Request.Error("Customer can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    public static final Request.Error MISSING_SEAT_ERROR = new Request.Error("Seat can't be null");

    int projectionId;
    int seatId;
    String email;
    CreditCard creditCard;
    TicketOption ticketOption;

    public PaymentRequest(String sessionToken, int projectionId, int seatId, String email, CreditCard creditCard, TicketOption ticketOption) {
        super(sessionToken);
        this.projectionId = projectionId;
        this.seatId = seatId;
        this.email = email;
        this.creditCard = creditCard;
        this.ticketOption = ticketOption;
    }

    @Override
    public void onValidate() {
        if (creditCard.creditCardCvv.length() != 3) {
            addError(LENGTH_CVV_CREDIT_CARD_ERROR);
        }
        if (LocalDate.parse(creditCard.creditCardExpirationDate).isBefore(LocalDate.now())) {
            addError(EXPIRATION_CREDIT_CARD_ERROR);
        }
        if (!creditCard.creditCardCvv.matches("[0-9]+")) {
            addError(CVV_LETTERS_ERROR);
        }
        if (!creditCard.creditCardNumber.matches("[0-9]+")) {
            addError(NUMBER_OF_CARD_LETTERS_ERROR);
        }
        if (creditCard.creditCardNumber.length() < 12 || creditCard.creditCardNumber.length() > 16) {
            addError(CREDIT_CARD_LENGTH_ERROR);
        }
        if (!email.contains("@")) {
            addError(EMAIL_ERROR);
        }
        if (ticketOption.magicBoxOption == null) {
            addError(MISSING_OPTION_ONE_ERROR);
        }
        if (ticketOption.openBarOption == null) {
            addError(MISSING_OPTION_TWO_ERROR);
        }
        if (ticketOption.skipLineOption == null) {
            addError(MISSING_OPTION_THREE_ERROR);
        }
    }

    @Value
    public static class CreditCard {

        String creditCardNumber;
        String creditCardCvv;
        String creditCardExpirationDate;

    }

    @Value
    public static class TicketOption {

        Boolean openBarOption;
        Boolean magicBoxOption;
        Boolean skipLineOption;

    }

}
