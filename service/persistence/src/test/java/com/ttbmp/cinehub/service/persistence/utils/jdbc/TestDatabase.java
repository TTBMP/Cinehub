package com.ttbmp.cinehub.service.persistence.utils.jdbc;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

/**
 * @author Fabio Buracchi
 */
@Database(
        version = 1,
        url = "mysql://localhost:3306/test_schema",
        user = "admin",
        password = "admin",
        timezone = "Europe/Rome",
        driverClassName = "com.mysql.cj.jdbc.Driver",
        entities = {
                TestEntity.class
        }
)

public interface TestDatabase extends JdbcDataSource {

    TestDao getTestDao() throws DataSourceMethodException;

}
