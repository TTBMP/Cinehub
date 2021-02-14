package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(String userId);

    Employee getEmployee(Shift shift);

}
