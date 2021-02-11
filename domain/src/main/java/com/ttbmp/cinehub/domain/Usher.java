package com.ttbmp.cinehub.domain;

public class Usher extends Employee {

    public Usher(User user, Cinema cinema) {
        super(user, cinema);
    }

    public Usher(String id, String name, String surname, String email, CreditCard creditCard, Cinema cinema) {
        super(id, name, surname, email, creditCard, cinema);
    }

}