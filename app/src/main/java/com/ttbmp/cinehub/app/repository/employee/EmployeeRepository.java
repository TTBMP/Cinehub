package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(String userId) throws RepositoryException;

    Employee getEmployee(Shift shift) throws RepositoryException;

}
