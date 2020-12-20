package com.ttbmp.cinehub.data.utils.jdbc.datasource;

import com.ttbmp.cinehub.data.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceClassException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcDataSourceProvider {

    private static final Map<Class<? extends JdbcDataSource>, Object> DATA_SOURCE_INSTANCE_MAP = new HashMap<>();

    private JdbcDataSourceProvider() {
    }

    public static <T extends JdbcDataSource> T getDataSource(@NotNull Class<T> dataSourceClass)
            throws DataSourceClassException, SQLException, ClassNotFoundException {

        DATA_SOURCE_INSTANCE_MAP.putIfAbsent(dataSourceClass, createDataSource(dataSourceClass));
        return dataSourceClass.cast(DATA_SOURCE_INSTANCE_MAP.get(dataSourceClass));
    }

    private static <T extends JdbcDataSource> T createDataSource(@NotNull Class<T> dataSourceClass)
            throws DataSourceClassException, SQLException, ClassNotFoundException {

        Database databaseAnnotation = dataSourceClass.getAnnotation(Database.class);
        if (databaseAnnotation == null) {
            throw new DataSourceClassException();
        }
        return dataSourceClass.cast(Proxy.newProxyInstance(
                dataSourceClass.getClassLoader(),
                new Class[]{dataSourceClass},
                new JdbcDataSourceInvocationHandler(databaseAnnotation)
        ));
    }

}
