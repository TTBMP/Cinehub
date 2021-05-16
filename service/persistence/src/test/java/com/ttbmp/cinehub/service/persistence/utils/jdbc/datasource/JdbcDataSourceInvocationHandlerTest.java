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
    void getDataSourceWithInvalidEntities_ThrowsException() {
        assertThrows(
                DataSourceClassException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidEntities.class)
        );
    }

    @Test
    void getDataSourceInvalidDao_ThrowsException() {
        assertThrows(
                DataSourceMethodException.class,
                () -> JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidDaoMethod.class).getInvalidDao()
        );
    }

    @Database(
            version = 1,
            url = "mysql://localhost:3306/cinemadb",
            user = "admin",
            password = "admin",
            timezone = "Europe/Rome",
            driverClassName = "com.mysql.cj.jdbc.Driver",
            entities = {DataSourceWithInvalidEntities.InvalidEntity.class}
    )
    private interface DataSourceWithInvalidEntities extends JdbcDataSource {
        class InvalidEntity {
        }
    }

    @Database(
            version = 1,
            url = "mysql://localhost:3306/cinemadb",
            user = "admin",
            password = "admin",
            timezone = "Europe/Rome",
            driverClassName = "com.mysql.cj.jdbc.Driver",
            entities = {}
    )
    private interface DataSourceWithInvalidDaoMethod extends JdbcDataSource {
        InvalidDao getInvalidDao() throws DataSourceMethodException;

        interface InvalidDao {
        }
    }

}