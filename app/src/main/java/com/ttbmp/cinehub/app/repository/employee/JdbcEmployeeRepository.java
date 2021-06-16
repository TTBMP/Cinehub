package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeRepository extends JdbcRepository implements EmployeeRepository {

    public JdbcEmployeeRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Employee getEmployee(String employeeId) throws RepositoryException {
        try {
            var employee = getDao(EmployeeDao.class).getEmployeeById(employeeId);
            return getEmployee(employee);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Employee getEmployee(Shift shift) throws RepositoryException {
        try {
            var employee = getDao(EmployeeDao.class).getEmployeeByShiftId(shift.getId());
            return getEmployee(employee);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getEmployeeList(Cinema cinema) throws RepositoryException {
        try {
            var employeeList = getDao(EmployeeDao.class).getEmployeeList(cinema.getId());
            return getEmployeeList(employeeList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private List<Employee> getEmployeeList(List<com.ttbmp.cinehub.service.persistence.entity.Employee> employeeList) throws RepositoryException {
        List<Employee> list = new ArrayList<>();
        for (var employee : employeeList) {
            list.add(getEmployee(employee));
        }
        return list;
    }

    private Employee getEmployee(com.ttbmp.cinehub.service.persistence.entity.Employee employee) throws RepositoryException {
        var roleList = getServiceLocator().getService(UserRepository.class).getUser(employee.getIdUser()).getRoleList();
        if (roleList.contains(Role.USHER_ROLE)) {
            return new UsherProxy(getServiceLocator(), employee.getIdUser());
        } else if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
            return new ProjectionistProxy(getServiceLocator(), employee.getIdUser());
        } else {
            throw new RepositoryException("Not an employee.");
        }
    }

}
