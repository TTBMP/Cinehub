package com.ttbmp.cinehub.core.entity;


/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Employee extends User {

    private String role;

    private Cinema cinema;
    private int weeklyWorkingHours;

    protected Employee() {
        setName("");
    }


    protected Employee(String name, String surname, String role, Cinema cinema, int weeklyWorkingHours) {
        setName(name);
        setSurname(surname);
        this.role = role;

        this.cinema = cinema;
        this.weeklyWorkingHours = weeklyWorkingHours;
    }

    public abstract Shift createShift(Employee employee, String date, String start, String end, Hall hall);

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
                && this.getRole().equals(other.getRole())
                && this.getCinema().equals(other.getCinema())
                && this.getWeeklyWorkingHours() == other.getWeeklyWorkingHours();
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }

}
