package com.ttbmp.cinehub.domain;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Employee extends User {

    private Cinema cinema;

    protected Employee(User user, Cinema cinema) {
        super(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCreditCard());
        this.cinema = cinema;
    }

    protected Employee(String id, String name, String surname, String email, CreditCard creditCard, Cinema cinema) {
        super(id, name, surname, email, creditCard);
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
