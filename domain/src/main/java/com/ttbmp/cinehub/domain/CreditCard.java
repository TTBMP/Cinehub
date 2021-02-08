package com.ttbmp.cinehub.domain;

/**
 * @author Palmieri Ivan
 */
public class CreditCard {
    private final String expirationDate;
    private final Integer cvv;
    private String number;
    private String pin;

    public CreditCard(String expirationDate, int cvv, String number, String pin) {
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
