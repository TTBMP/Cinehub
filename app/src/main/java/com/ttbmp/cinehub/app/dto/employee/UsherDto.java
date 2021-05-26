package com.ttbmp.cinehub.app.dto.employee;

import com.ttbmp.cinehub.domain.employee.Usher;

/**
 * @author Fabio Buracchi
 */
public class UsherDto extends EmployeeDto {

    public UsherDto(Usher usher) {
        super(usher);
    }

    @Override
    public String toString() {
        return "usher";
    }

}
