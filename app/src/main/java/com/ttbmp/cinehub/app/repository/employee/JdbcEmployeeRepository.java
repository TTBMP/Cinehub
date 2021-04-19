package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcEmployeeRepository implements EmployeeRepository{

    private final ServiceLocator serviceLocator;

    private EmployeeDao employeeDao = null;

    public JdbcEmployeeRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Override
    public Employee getEmployee(String userId)  {
        return null;
    }

    @Override
    public Employee getEmployee(Shift shift) {
        return null;
    }


    private EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            try {
                this.employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return employeeDao;
    }
}
