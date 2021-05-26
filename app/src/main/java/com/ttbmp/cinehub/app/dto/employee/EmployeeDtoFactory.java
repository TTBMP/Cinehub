package com.ttbmp.cinehub.app.dto.employee;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;

public class EmployeeDtoFactory {

    private EmployeeDtoFactory() {

    }

    public static EmployeeDto getEmployeeDto(Employee employee) {
        if (employee instanceof Projectionist) {
            return getEmployeeDto((Projectionist) employee);
        }
        if (employee instanceof Usher) {
            return getEmployeeDto((Usher) employee);
        }
        throw new IllegalStateException("Unexpected value: " + employee);
    }

    public static EmployeeDto getEmployeeDto(Projectionist projectionist) {
        return new ProjectionistDto(projectionist);
    }

    public static EmployeeDto getEmployeeDto(Usher usher) {
        return new UsherDto(usher);
    }

}
