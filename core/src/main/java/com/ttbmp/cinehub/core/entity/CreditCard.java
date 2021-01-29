package com.ttbmp.cinehub.core.entity;

/**
 * @author Palmieri Ivan
 */
public class CreditCard {
    private String expirationDate;
    private Integer cvv;
    private String number;
    private String pin;

    public CreditCard(String expirationDate, int cvv, String number, String pin) {
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.number = number;
        this.pin = pin;//toglierlo
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
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
