package com.ttbmp.cinehub.data.local.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.data.local.CinemaDao;
import com.ttbmp.cinehub.data.local.CinemaDatabase;
import com.ttbmp.cinehub.data.local.CinemaDto;
import com.ttbmp.cinehub.data.local.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoInsertOperationTest {

    @Test
    void execute_InsertDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(() -> dao.insert(new CinemaDto(0, "pippo", "pluto", "paperino")));
    }

    @Test
    void execute_InsertListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        List<CinemaDto> dtoList = Arrays.asList(
                new CinemaDto(0, "pippo", "pluto", "paperino"),
                new CinemaDto(0, "qui", "quo", "qua")
        );
        assertDoesNotThrow(() -> dao.insert(dtoList));
    }

}