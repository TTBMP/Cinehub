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
class DaoUpdateOperationTest {

    @Test
    void execute_UpdateDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        dao.insert(new TestEntity(0, "pippo"));
        var entity = dao.getByField("pippo");
        entity.setField("pluto");
        assertDoesNotThrow(() -> dao.update(entity));
    }

    @Test
    void execute_UpdateListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(TestDatabase.class).getTestDao();
        dao.insert(Arrays.asList(new TestEntity(0, "pluto"), new TestEntity(0, "paperino")));
        var entityList = Arrays.asList(
                dao.getByField("pluto"),
                dao.getByField("paperino")
        );
        assertDoesNotThrow(() -> dao.update(entityList));
    }

}
