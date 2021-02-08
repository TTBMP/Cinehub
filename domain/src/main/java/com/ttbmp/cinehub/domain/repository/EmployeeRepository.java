package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.entity.Employee;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(int userId);

}
