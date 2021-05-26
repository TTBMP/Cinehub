package com.ttbmp.cinehub.app.dto.employee;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.domain.employee.Employee;

/**
 * @author Massimo Mazzetti
 */
public abstract class EmployeeDto {

    private String id;
    private String name;
    private String surname;
    private CinemaDto cinema;

    protected EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.cinema = new CinemaDto(employee.getCinema());
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
    public abstract String toString();

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

