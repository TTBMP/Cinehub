package com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fabio Buracchi
 */
class JdbcDataSourceProviderTest {

    Class<? extends JdbcDataSource> dataSourceClass = CinemaDatabase.class;

    @Test
    void getDataSource_ReturnsValue_ValidDataSource() throws DataSourceClassException, SQLException, ClassNotFoundException {
        JdbcDataSource dataSource = JdbcDataSourceProvider.getDataSource(dataSourceClass);
        assertNotNull(dataSource);
    }

    @Test
    void getDataSource_ImplementsMultiton_ReturnSameInstance() throws DataSourceClassException, SQLException, ClassNotFoundException {
        assertSame(JdbcDataSourceProvider.getDataSource(dataSourceClass), JdbcDataSourceProvider.getDataSource(dataSourceClass));
    }

    @Test
    void getDataSource_GetClassWithoutAnnotation_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithoutDatabaseAnnotation.class)
        );
    }

    private interface DataSourceWithoutDatabaseAnnotation extends JdbcDataSource {
    }

}