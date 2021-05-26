package com.ttbmp.cinehub.app.dto.employee;

import com.ttbmp.cinehub.domain.employee.Projectionist;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistDto extends EmployeeDto {

    public ProjectionistDto(Projectionist projectionist) {
        super(projectionist);
    }

    @Override
    public String toString() {
        return "projectionist";
    }

}
