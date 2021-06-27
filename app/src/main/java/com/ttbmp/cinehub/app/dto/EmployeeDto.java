package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;
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
public class EmployeeDto {

    String id;
    String name;
    String surname;
    CinemaDto cinema;
    EmployeeRole role;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.cinema = new CinemaDto(employee.getCinema());
        if (employee instanceof Projectionist) {
            this.role = EmployeeRole.PROJECTIONIST;
        } else if (employee instanceof Usher) {
            this.role = EmployeeRole.USHER;
        } else {
            throw new IllegalStateException("Unexpected value: " + employee);
        }
    }

    public enum EmployeeRole {
        PROJECTIONIST("Projectionist"),
        USHER("Usher");

        private final String name;

        EmployeeRole(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
