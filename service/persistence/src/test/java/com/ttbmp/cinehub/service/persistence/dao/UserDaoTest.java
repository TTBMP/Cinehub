package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
class UserDaoTest {

    @Test
    void getUserById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        UserDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUserDao();
        assertDoesNotThrow(() -> dao.getUserById("1"));
    }

    @Test
    void getUserByTicket() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        UserDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUserDao();
        assertDoesNotThrow(() -> dao.getUserByTicket(5));
    }

}
