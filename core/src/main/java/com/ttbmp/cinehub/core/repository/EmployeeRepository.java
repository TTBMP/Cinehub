package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Employee;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(int userId);

}
