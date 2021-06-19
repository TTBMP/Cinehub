package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface EmployeeRepository {

    Employee getEmployee(String employeeId) throws RepositoryException;

    Employee getEmployee(Shift shift) throws RepositoryException;

    List<Employee> getAllEmployee() throws RepositoryException;

    List<Employee> getEmployeeList(Cinema cinema) throws RepositoryException;

}
