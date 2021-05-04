package com.ttbmp.cinehub.app.dto;

public class CreditCardDto {

    private int id;
    private String number;
    private String cvv;
    private String expirationDate;

    public CreditCardDto(int id, String number, String cvv, String expirationDate) {
        this.id = id;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
