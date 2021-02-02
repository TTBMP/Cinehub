package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Employee extends User {


    private Cinema cinema;

    protected Employee() {
        setName("");
    }

    protected Employee(String name, String surname, Cinema cinema) {
        setName(name);
        setSurname(surname);
        this.cinema = cinema;
    }

    public abstract Shift createShift(String date, String start, String end, Hall hall);

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Employee other = (Employee) obj;
        return this.getName().equals(other.getName())
                && this.getSurname().equals(other.getSurname())
                && this.getCinema().equals(other.getCinema());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
