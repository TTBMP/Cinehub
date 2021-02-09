package com.ttbmp.cinehub.domain;

/**
 * @author Fabio Buracchi, Palmieri Ivan
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private CreditCard creditCard;

    public User(int id, String name, String surname, String email, CreditCard creditCard) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.creditCard = creditCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        return id == other.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

}
