package com.ttbmp.cinehub.data.utils.jdbc.datasource;

import com.ttbmp.cinehub.data.CinemaDatabase;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceClassException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.ttbmp.cinehub.data.utils.jdbc.datasource.JdbcDataSourceProvider.getDataSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JdbcDataSourceProviderTest {

    Class<? extends JdbcDataSource> dataSourceClass = CinemaDatabase.class;

    @Test
    void getDataSource_ReturnsValue_ValidDataSource() throws DataSourceClassException, SQLException, ClassNotFoundException {
        JdbcDataSource dataSource = getDataSource(dataSourceClass);
        assertNotNull(dataSource);
    }

    @Test
    void getDataSource_ImplementsMultiton_ReturnSameInstance() throws DataSourceClassException, SQLException, ClassNotFoundException {
        assert (getDataSource(dataSourceClass) == getDataSource(dataSourceClass));
    }

    @Test
    void getDataSource_GetClassWithoutAnnotation_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> getDataSource(DataSourceWithoutDatabaseAnnotation.class)
        );
    }

    private interface DataSourceWithoutDatabaseAnnotation extends JdbcDataSource {
    }

}