package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public abstract class DaoOperation {

    protected final Method method;
    protected final Connection connection;
    protected final List<Class<?>> entityTypeList;
    protected boolean requireCollection;
    protected Class<?> entityType;
    protected String queryTemplate;

    protected DaoOperation(Method method, Connection connection, List<Class<?>> entityTypeList) {
        this.method = method;
        this.connection = connection;
        this.entityTypeList = entityTypeList;
    }

    public abstract Object execute(Object[] args) throws DaoMethodException;

}
