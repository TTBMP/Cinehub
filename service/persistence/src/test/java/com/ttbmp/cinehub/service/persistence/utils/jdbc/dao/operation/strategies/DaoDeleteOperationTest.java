package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.TestEntity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoDeleteOperationTest {

    @Test
    void deleteOperation_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        dao.insert(new TestEntity(0, "pippo"));
        var entity = dao.getByField("pippo");
        assertDoesNotThrow(() -> dao.delete(entity));
    }


    @Test
    void deleteListOperation_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        var entityList = Arrays.asList(new TestEntity(0, "pluto"), new TestEntity(0, "paperino"));
        dao.insert(entityList);
        entityList = Arrays.asList(
                dao.getByField("pluto"),
                dao.getByField("paperino")
        );
        var finalDtoList = entityList;
        assertDoesNotThrow(() -> dao.delete(finalDtoList));
    }

}
