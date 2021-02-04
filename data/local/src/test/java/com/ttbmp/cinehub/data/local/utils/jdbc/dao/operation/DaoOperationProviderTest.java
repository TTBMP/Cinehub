package com.ttbmp.cinehub.data.local.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.data.local.CinemaDao;
import com.ttbmp.cinehub.data.local.CinemaDatabase;
import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.local.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.data.local.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Fabio Buracchi
 */
class DaoOperationProviderTest {

    @Test
    void getDaoOperation_ReturnsValidOperation_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, NoSuchMethodException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(dao::getAllCinema);
    }

    @Test
    void getDaoOperation_getOperationWithoutRequiredAnnotation_ThrowException() throws DataSourceClassException, SQLException, ClassNotFoundException, NoSuchMethodException, DataSourceMethodException, DaoMethodException {
        DaoWithInvalidDaoMethod dao;
        dao = JdbcDataSourceProvider.getDataSource(DataSourceWithInvalidDaoMethod.class).DaoWithInvalidDaoMethod();
        assertThrows(DaoMethodException.class, dao::invalidMethod);
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
        DaoWithInvalidDaoMethod DaoWithInvalidDaoMethod() throws DataSourceMethodException;
    }

    @Dao
    private interface DaoWithInvalidDaoMethod {
        Object invalidMethod() throws DaoMethodException;
    }

}