package com.ttbmp.cinehub.app.dto.employee;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Massimo Mazzetti
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public abstract class EmployeeDto {

    String id;
    String name;
    String surname;
    CinemaDto cinema;

    protected EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.cinema = new CinemaDto(employee.getCinema());
    }

}

