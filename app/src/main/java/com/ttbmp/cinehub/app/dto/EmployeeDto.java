package com.ttbmp.cinehub.app.dto;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeDto {

    private String id;
    private String name;
    private String surname;
    private CinemaDto cinema;

    public EmployeeDto(String id, String name, String surname, CinemaDto cinema) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cinema = cinema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (EmployeeDto) obj;
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}

