package com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fabio Buracchi
 */
class JdbcDataSourceProviderTest {

    Class<? extends JdbcDataSource> dataSourceClass = TestDatabase.class;

    @Test
    void getDataSource_ReturnsNotNull() throws DataSourceClassException, SQLException, ClassNotFoundException {
        var dataSource = JdbcDataSourceProvider.getDataSource(dataSourceClass);
        assertNotNull(dataSource);
    }

    @Test
    void getDataSource_ReturnSameInstance() throws DataSourceClassException, SQLException, ClassNotFoundException {
        assertSame(
                JdbcDataSourceProvider.getDataSource(dataSourceClass),
                JdbcDataSourceProvider.getDataSource(dataSourceClass)
        );
    }

    @Test
    void getDataSourceInterfaceWithoutDatabaseAnnotation_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithoutDatabaseAnnotation.class)
        );
    }

    private interface DataSourceWithoutDatabaseAnnotation extends JdbcDataSource {
    }

}