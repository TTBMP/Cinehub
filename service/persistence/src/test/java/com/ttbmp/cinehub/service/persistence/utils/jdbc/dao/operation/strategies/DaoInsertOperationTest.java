package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestEntity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoInsertOperationTest {

    @Test
    void insertOperation_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        assertDoesNotThrow(() -> dao.insert(new TestEntity(0, "pippo")));
    }

    @Test
    void insertListOperation_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        var entityList = Arrays.asList(
                new TestEntity(0, "pluto"),
                new TestEntity(0, "paperino")
        );
        assertDoesNotThrow(() -> dao.insert(entityList));
    }

}
