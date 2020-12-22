package com.ttbmp.cinehub.data.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.data.utils.jdbc.exception.DaoMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public abstract class DaoOperation {

    protected final Method method;
    protected final Connection connection;
    protected final List<Class<?>> dataSourceEntityList;
    protected Class<?> objectType;
    protected Class<?> dtoType;
    protected String queryTemplate;

    protected DaoOperation(Method method, Connection connection, List<Class<?>> dataSourceEntityList) {
        this.method = method;
        this.connection = connection;
        this.dataSourceEntityList = dataSourceEntityList;
    }

    public abstract Object execute(Object[] args) throws DaoMethodException, InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException;

}
