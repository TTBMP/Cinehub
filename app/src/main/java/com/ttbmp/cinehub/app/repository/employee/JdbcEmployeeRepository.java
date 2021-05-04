package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

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


    private Employee getEmployee(com.ttbmp.cinehub.service.persistence.entity.Employee employee) {
        if (employee.getRole().equals("maschera")) {
            return new UsherProxy(
                    employee.getIdUser(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(CinemaRepository.class),
                    serviceLocator.getService(ShiftRepository.class)
            );
        } else if (employee.getRole().equals("proiezionista")) {
            return new ProjectionistProxy(
                    employee.getIdUser(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(CinemaRepository.class),
                    serviceLocator.getService(ShiftRepository.class)
            );
        } else {
            return null;
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

}
