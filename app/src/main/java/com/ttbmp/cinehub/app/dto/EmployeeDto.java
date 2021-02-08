package com.ttbmp.cinehub.app.dto;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeDto {

    private int id;
    private String name;
    private String surname;
    private CinemaDto cinema;

    public EmployeeDto(int id, String name, String surname, CinemaDto cinema) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cinema = cinema;
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

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
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
        EmployeeDto other = (EmployeeDto) obj;
        return this.getName().equals(other.getName())
                && this.getSurname().equals(other.getSurname())
                && this.getCinema().equals(other.getCinema());
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }

}

