package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.domain.Employee;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(int userId);

}
