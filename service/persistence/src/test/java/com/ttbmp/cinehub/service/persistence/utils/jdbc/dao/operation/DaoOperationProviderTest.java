package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fabio Buracchi
 */
class DaoOperationProviderTest {

    @Test
    void getDaoOperation_ReturnsValidOperation() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        assertNotNull(JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao().getAll());
    }

    @Test
    void getDaoOperation_DoesNotThrowException() {
        assertDoesNotThrow(() -> JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao().getAll());
    }

    @Test
    void getDaoOperationWithoutRequiredAnnotations_ThrowsException() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidDaoMethod.class).getDaoWithInvalidMethod();
        assertThrows(DaoMethodException.class, dao::invalidMethod);
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
        DaoWithInvalidMethod getDaoWithInvalidMethod() throws DataSourceMethodException;

        @Dao
        interface DaoWithInvalidMethod {
            Object invalidMethod() throws DaoMethodException;
        }
    }

}
