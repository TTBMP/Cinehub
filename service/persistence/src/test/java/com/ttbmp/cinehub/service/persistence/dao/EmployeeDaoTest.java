package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Ivan Palmieri
 */

class EmployeeDaoTest {

    @Test
    void getEmployeeById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        EmployeeDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
        assertDoesNotThrow(() -> dao.getEmployeeById("1"));
    }

    @Test
    void getEmployeeByShiftId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        EmployeeDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
        assertDoesNotThrow(() -> dao.getEmployeeByShiftId(1));
    }


}