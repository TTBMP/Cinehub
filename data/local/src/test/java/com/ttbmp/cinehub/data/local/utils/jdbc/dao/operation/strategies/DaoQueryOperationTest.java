package com.ttbmp.cinehub.data.local.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.data.local.CinemaDao;
import com.ttbmp.cinehub.data.local.CinemaDatabase;
import com.ttbmp.cinehub.data.local.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoQueryOperationTest {

    @Test
    void execute_ReturnsDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(() -> dao.getCinema("multisala"));
    }

    @Test
    void execute_ReturnsListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(dao::getAllCinema);
    }

}