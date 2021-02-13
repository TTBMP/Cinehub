package com.ttbmp.cinehub.domain;


public class Projectionist extends Employee {

    public Projectionist(User user, Cinema cinema) {
        super(user, cinema);
    }

    public Projectionist(String id, String name, String surname, String email, CreditCard creditCard, Cinema cinema) {
        super(id, name, surname, email, creditCard, cinema);
    }

}
