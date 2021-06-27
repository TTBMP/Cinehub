package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperationProvider;

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
    List<Class<?>> entityTypeList;
    DaoOperationProvider operationProvider;

    public DaoInvocationHandler(@NotNull Connection connection, Class<?>[] dataSourceEntities) {
        this.connection = connection;
        entityTypeList = Arrays.asList(dataSourceEntities);
        operationProvider = new DaoOperationProvider();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var operation = operationProvider.getOperation(method, connection, entityTypeList);
        return operation.execute(args);
    }

}
