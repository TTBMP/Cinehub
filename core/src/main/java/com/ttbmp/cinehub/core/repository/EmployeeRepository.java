package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Result<Employee> getEmployee(int userId);

}
