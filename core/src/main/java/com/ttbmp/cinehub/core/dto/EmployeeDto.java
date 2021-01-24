package com.ttbmp.cinehub.core.dto;

/**
 * @author Massimo Mazzetti
 */

public class EmployeeDto {

    private String name;
    private String surname;
    private String role;
    private int hourRemain;
    private int minRemain;
    private CinemaDto cinema;

    public EmployeeDto(String name, String surname, String role, int hourRemain, int minRemain, CinemaDto cinema) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.hourRemain = hourRemain;
        this.minRemain = minRemain;
        this.cinema = cinema;
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        EmployeeDto other = (EmployeeDto) obj;
        return this.getName().equals(other.getName())
                && this.getSurname().equals(other.getSurname())
                && this.getRole().equals(other.getRole())
                && this.getHourRemain() == other.getHourRemain()
                && this.getMinRemain() == other.getMinRemain()
                && this.getCinema().equals(other.getCinema());
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }
}

