package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class Employee extends User {

    private String role;
    private int hourRemain;
    private int minRemain;

    private Cinema cinema;
    private int weeklyWorkingHours;

    public Employee() {
        setName("");
    }

    public Employee(String name, String surname, String role, int hourRemain, int minRemain, Cinema cinema, int weeklyWorkingHours) {
        setName(name);
        setSurname(surname);
        this.role = role;
        this.minRemain = minRemain;
        this.hourRemain = hourRemain;
        this.cinema = cinema;
        this.weeklyWorkingHours = weeklyWorkingHours;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getHourRemain() {
        return hourRemain;
    }

    public void setHourRemain(int hourRemain) {
        this.hourRemain = hourRemain;
    }

    public int getMinRemain() {
        return minRemain;
    }

    public void setMinRemain(int minRemain) {
        this.minRemain = minRemain;
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
                && this.getHourRemain() == other.getHourRemain()
                && this.getMinRemain() == other.getMinRemain()
                && this.getCinema().equals(other.getCinema())
                && this.getWeeklyWorkingHours() == other.getWeeklyWorkingHours();
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }

}
