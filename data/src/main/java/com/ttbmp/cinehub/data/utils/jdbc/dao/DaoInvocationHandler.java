package com.ttbmp.cinehub.data.utils.jdbc.dao;

import com.ttbmp.cinehub.data.utils.jdbc.dao.operation.DaoOperation;
import com.ttbmp.cinehub.data.utils.jdbc.dao.operation.DaoOperationProvider;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class DaoInvocationHandler implements InvocationHandler {

    Connection connection;
    List<Class<?>> dataSourceEntityList;
    DaoOperationProvider operationProvider;

    public DaoInvocationHandler(@NotNull Connection connection, Class<?>[] dataSourceEntities) {
        this.connection = connection;
        dataSourceEntityList = Arrays.asList(dataSourceEntities);
        operationProvider = new DaoOperationProvider();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DaoOperation operation = operationProvider.getDaoOperation(method, connection, dataSourceEntityList);
        return operation.execute(args);
    }

}
