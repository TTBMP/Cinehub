package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.Seat;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class PaymentRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_EMAIL_ERROR = new Request.Error("Email can't be null");
    public static final Request.Error MISSING_CREDIT_CARD_ERROR = new Request.Error("Credit card can't be null");
    public static final Request.Error LENGTH_CVV_CREDIT_CARD_ERROR = new Request.Error("The CVV must be three numbers in length");
    public static final Request.Error EXPIRATION_CREDIT_CARD_ERROR = new Request.Error("You cannot select a date in the past");
    public static final Request.Error EMAIL_ERROR = new Request.Error("The email entered is syntactically incorrect");
    public static final Request.Error CREDIT_CARD_LENGTH_ERROR = new Request.Error("The credit card must have a minimum length of 12 characters and a maximum of 16 characters");
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
    private String creditCardNumber;
    private String creditCardCvv;
    private String creditCardExpirationDate;
    private Boolean openBarOption;
    private Boolean magicBoxOption;
    private Boolean skipLineOption;

    public PaymentRequest(
            String sessionToken,
            int projectionId,
            int seatId,
            String email,
            String creditCardNumber,
            String creditCardCvv,
            String creditCardExpirationDate,
            Boolean openBarOption,
            Boolean magicBoxOption,
            Boolean skipLineOption) {
        super(sessionToken);
        this.projectionId = projectionId;
        this.seatId = seatId;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.creditCardCvv = creditCardCvv;
        this.creditCardExpirationDate = creditCardExpirationDate;
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
        if (creditCardCvv.length() != 3) {
            addError(LENGTH_CVV_CREDIT_CARD_ERROR);
        }
        if (LocalDate.parse(creditCardExpirationDate).isBefore(LocalDate.now())) {
            addError(EXPIRATION_CREDIT_CARD_ERROR);
        }
        if (!creditCardCvv.matches("[0-9]+")) {
            addError(CVV_LETTERS_ERROR);
        }
        if (!creditCardNumber.matches("[0-9]+")) {
            addError(NUMBER_OF_CARD_LETTERS_ERROR);
        }
        if (creditCardNumber.length() < 12 || creditCardNumber.length() > 16) {
            addError(CREDIT_CARD_LENGTH_ERROR);
        }
        if (!email.contains("@")) {
            addError(EMAIL_ERROR);
        }
        if (!email.contains(".")) {
            addError(EMAIL_ERROR);
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
    }

    public void semanticValidate(Customer customer, Projection projection, Seat seat) throws InvalidRequestException {
        if (customer == null) {
            addError(MISSING_CUSTOMER_ERROR);
        }if (projection == null) {
            addError(MISSING_PROJECTION_ERROR);
        }if (seat == null) {
            addError(MISSING_SEAT_ERROR);
        }
        if (!getErrorList().isEmpty()) {
            throw new InvalidRequestException();
        }
    }
}
