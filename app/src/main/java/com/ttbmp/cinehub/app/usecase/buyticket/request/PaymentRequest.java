package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
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

    private int projectionId;
    private int seatId;
    private String email;
    private CreditCard creditCard;
    private TicketOption ticketOption;

    public PaymentRequest(
            String sessionToken,
            int projectionId,
            int seatId,
            String email,
            CreditCard creditCard,
            TicketOption ticketOption) {
        super(sessionToken);
        this.projectionId = projectionId;
        this.seatId = seatId;
        this.email = email;
        this.creditCard = creditCard;
        this.ticketOption = ticketOption;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public TicketOption getTicketOption() {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) {
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

    public static class CreditCard {

        private String creditCardNumber;
        private String creditCardCvv;
        private String creditCardExpirationDate;

        public CreditCard(String creditCardNumber, String creditCardCvv, String creditCardExpirationDate) {
            this.creditCardNumber = creditCardNumber;
            this.creditCardCvv = creditCardCvv;
            this.creditCardExpirationDate = creditCardExpirationDate;
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
    }

    public static class TicketOption {

        private Boolean openBarOption;
        private Boolean magicBoxOption;
        private Boolean skipLineOption;

        public TicketOption(Boolean openBarOption, Boolean magicBoxOption, Boolean skipLineOption) {
            this.openBarOption = openBarOption;
            this.magicBoxOption = magicBoxOption;
            this.skipLineOption = skipLineOption;
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

    }

}
