package com.ttbmp.cinehub.data.utils.jdbc.datasource;

import com.ttbmp.cinehub.data.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import static com.ttbmp.cinehub.data.utils.jdbc.datasource.JdbcDataSourceProvider.getDataSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JdbcDataSourceInvocationHandlerTest {

    @Test
    void JdbcDataSourceInvocationHandler_instantiateClassWithInvalidEntities_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> getDataSource(DataSourceWithInvalidEntities.class)
        );
    }

    @Test
    void invoke_instantiateDaoWithoutAnnotation_ThrowsException() {
        assertThrows(
                DataSourceMethodException.class,
                () -> getDataSource(DataSourceWithInvalidDaoMethod.class).InvalidDao()
        );
    }

    @Database(
            version = 1,
            url = "mysql://localhost:3306/cinemadb",
            user = "admin",
            password = "admin",
            timezone = "Europe/Rome",
            driverClassName = "com.mysql.cj.jdbc.Driver",
            entities = {
                    Object.class
            }
    )
    private interface DataSourceWithInvalidEntities extends JdbcDataSource {
    }

    @Database(
            version = 1,
            url = "mysql://localhost:3306/cinemadb",
            user = "admin",
            password = "admin",
            timezone = "Europe/Rome",
            driverClassName = "com.mysql.cj.jdbc.Driver",
            entities = {

            }
    )
    private interface DataSourceWithInvalidDaoMethod extends JdbcDataSource {
        InvalidDao InvalidDao() throws DataSourceMethodException;
    }

    private interface InvalidDao {
    }
}