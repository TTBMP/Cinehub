package com.ttbmp.cinehub.data.local.utils.jdbc.datasource;

import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import static com.ttbmp.cinehub.data.local.utils.jdbc.datasource.JdbcDataSourceProvider.getDataSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Fabio Buracchi
 */
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
            url = "mysql://dbtest:3306/cinemadb",
            user = "root",
            password = "password",
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
            url = "mysql://dbtest:3306/cinemadb",
            user = "root",
            password = "password",
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