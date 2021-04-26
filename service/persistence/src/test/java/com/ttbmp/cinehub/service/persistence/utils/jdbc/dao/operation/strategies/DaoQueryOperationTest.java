package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.CinemaDao;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoQueryOperationTest {

    @Test
    void execute_ReturnsDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(() -> dao.getCinema("multisala"));
    }

    @Test
    void execute_ReturnsListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(dao::getAllCinema);
    }

}