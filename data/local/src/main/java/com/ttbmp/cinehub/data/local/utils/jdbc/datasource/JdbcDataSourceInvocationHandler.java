package com.ttbmp.cinehub.data.local.utils.jdbc.datasource;

import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.local.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.data.local.utils.jdbc.dao.DaoInvocationHandler;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.local.utils.jdbc.exception.DataSourceMethodException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Fabio Buracchi
 */
public class JdbcDataSourceInvocationHandler implements InvocationHandler {

    Database databaseAnnotation;
    Connection connection;

    public JdbcDataSourceInvocationHandler(@NotNull Database databaseAnnotation) throws SQLException, ClassNotFoundException, DataSourceClassException {
        this.databaseAnnotation = databaseAnnotation;
        this.connection = getConnection();
        for (Class<?> entityClass : databaseAnnotation.entities()) {
            if (entityClass.getAnnotation(Entity.class) == null) {
                throw new DataSourceClassException();
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws DataSourceMethodException {
        Class<?> daoClass = method.getReturnType();
        if (daoClass.getAnnotation(Dao.class) == null) {
            throw new DataSourceMethodException();
        }
        return Proxy.newProxyInstance(
                daoClass.getClassLoader(),
                new Class[]{daoClass},
                new DaoInvocationHandler(connection, databaseAnnotation.entities())
        );
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        loadDriver();
        String url = String.format(
                "jdbc:%s?user=%s&password=%s&serverTimezone=%s",
                databaseAnnotation.url(),
                databaseAnnotation.user(),
                databaseAnnotation.password(),
                databaseAnnotation.timezone()
        );
        return DriverManager.getConnection(url);
    }

    private void loadDriver() throws ClassNotFoundException {
        Class.forName(databaseAnnotation.driverClassName());
    }

}
