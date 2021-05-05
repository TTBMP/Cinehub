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

class HallDaoTest {

    @Test
    void getHallByCinemaId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getHallDao();
        assertDoesNotThrow(() -> dao.getHallByCinemaId(1));
    }

    @Test
    void getHallByProjectionId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getHallDao();
        assertDoesNotThrow(() -> dao.getHallByProjectionId(1));
    }

    @Test
    void getHallByProjectionistShift() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getHallDao();
        assertDoesNotThrow(() -> dao.getHallByProjectionistShift(1));
    }

    @Test
    void getHallById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getHallDao();
        assertDoesNotThrow(() -> dao.getHallById(1));
    }

}
