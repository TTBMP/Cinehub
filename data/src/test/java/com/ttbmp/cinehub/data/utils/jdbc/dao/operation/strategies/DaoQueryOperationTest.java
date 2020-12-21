package com.ttbmp.cinehub.data.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.data.CinemaDao;
import com.ttbmp.cinehub.data.CinemaDatabase;
import com.ttbmp.cinehub.data.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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