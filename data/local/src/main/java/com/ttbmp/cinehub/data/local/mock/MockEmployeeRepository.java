package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.repository.EmployeeRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository implements EmployeeRepository {

    @Override
    public Result<Employee> getEmployee(int userId) {
        /* return new Result<>(new Employee());*/
        return new Result<>(null);
    }

}
