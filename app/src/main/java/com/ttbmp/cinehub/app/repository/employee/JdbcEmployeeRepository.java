package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcEmployeeRepository implements EmployeeRepository {

    private final ServiceLocator serviceLocator;

    private EmployeeDao employeeDao = null;

    public JdbcEmployeeRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Override
    public Employee getEmployee(String employeeId) throws RepositoryException {
        try {
            var employee = getEmployeeDao().getEmployeeById(employeeId);
            return getEmployee(employee);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Employee getEmployee(Shift shift) throws RepositoryException {
        try {
            var employee = getEmployeeDao().getEmployeeByShiftId(shift.getId());
            return getEmployee(employee);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getEmployeeList(Cinema cinema) throws RepositoryException {
        try {
            var employeeList = getEmployeeDao().getEmployeeList(cinema.getId());
            return getEmployeeList(employeeList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private EmployeeDao getEmployeeDao() throws RepositoryException {
        if (employeeDao == null) {
            try {
                this.employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return employeeDao;
    }

    private List<Employee> getEmployeeList(List<com.ttbmp.cinehub.service.persistence.entity.Employee> employeeList) throws RepositoryException {
        List<Employee> list = new ArrayList<>();
        for (var employee : employeeList) {
            list.add(getEmployee(employee));
        }
        return list;
    }

    private Employee getEmployee(com.ttbmp.cinehub.service.persistence.entity.Employee employee) throws RepositoryException {
        var roleList = Arrays.asList(serviceLocator.getService(UserRepository.class).getUser(employee.getIdUser()).getRoles());
        if (roleList.contains(Role.USHER_ROLE)) {
            return new UsherProxy(
                    employee.getIdUser(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(CinemaRepository.class),
                    serviceLocator.getService(ShiftRepository.class)
            );
        } else if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
            return new ProjectionistProxy(
                    employee.getIdUser(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(CinemaRepository.class),
                    serviceLocator.getService(ShiftRepository.class)
            );
        } else {
            throw new RepositoryException("Not an employee.");
        }
    }

}
