package com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class JdbcDataSourceProvider {

    private static final Map<Class<? extends JdbcDataSource>, Object> DATA_SOURCE_INSTANCE_MAP = new HashMap<>();

    private JdbcDataSourceProvider() {
    }

    public static <T extends JdbcDataSource> T getDataSource(@NotNull Class<T> dataSourceClass)
            throws DataSourceClassException, SQLException, ClassNotFoundException {
        var result = DATA_SOURCE_INSTANCE_MAP.get(dataSourceClass);
        if (result == null) {
            result = createDataSource(dataSourceClass);
            DATA_SOURCE_INSTANCE_MAP.put(dataSourceClass, result);
        }
        return dataSourceClass.cast(result);
    }

    private static <T extends JdbcDataSource> T createDataSource(@NotNull Class<T> dataSourceClass)
            throws DataSourceClassException, SQLException, ClassNotFoundException {

        var databaseAnnotation = dataSourceClass.getAnnotation(Database.class);
        if (databaseAnnotation == null) {
            throw new DataSourceClassException(dataSourceClass + " does not have Database annotation.");
        }
        return dataSourceClass.cast(Proxy.newProxyInstance(
                dataSourceClass.getClassLoader(),
                new Class[]{dataSourceClass},
                new JdbcDataSourceInvocationHandler(databaseAnnotation)
        ));
    }

}
