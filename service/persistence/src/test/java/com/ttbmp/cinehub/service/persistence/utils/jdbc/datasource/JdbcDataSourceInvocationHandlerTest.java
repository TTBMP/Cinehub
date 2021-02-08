package com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Fabio Buracchi
 */
class JdbcDataSourceInvocationHandlerTest {

    @Test
    void JdbcDataSourceInvocationHandler_instantiateClassWithInvalidEntities_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidEntities.class)
        );
    }

    @Test
    void invoke_instantiateDaoWithoutAnnotation_ThrowsException() {
        assertThrows(
                DataSourceMethodException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidDaoMethod.class).InvalidDao()
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