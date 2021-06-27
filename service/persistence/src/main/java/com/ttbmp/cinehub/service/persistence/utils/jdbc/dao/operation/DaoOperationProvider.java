package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Delete;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Insert;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Update;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies.DaoDeleteOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies.DaoInsertOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies.DaoQueryOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies.DaoUpdateOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoOperationProvider {

    private static final List<Class<?>> requiredTypeList = Arrays.asList(
            Query.class,
            Insert.class,
            Update.class,
            Delete.class
    );
    private final Map<Method, DaoOperation> operationInstanceMap = new HashMap<>();

    public DaoOperation getOperation(
            @NotNull Method method,
            @NotNull Connection connection,
            List<Class<?>> entityTypeList) throws DaoMethodException {
        try {
            var result = operationInstanceMap.get(method);
            if (result == null) {
                result = createOperation(method, connection, entityTypeList);
                operationInstanceMap.put(method, result);
            }
            return result;
        } catch (NoSuchMethodException e) {
            throw new DaoMethodException(e.getMessage());
        }
    }

    private DaoOperation createOperation(
            @NotNull Method method,
            @NotNull Connection connection,
            List<Class<?>> entityTypeList) throws DaoMethodException, NoSuchMethodException {
        Type type = getOperationType(method);
        if (type == Query.class) {
            return new DaoQueryOperation(method, connection, entityTypeList);
        } else if (type == Insert.class) {
            return new DaoInsertOperation(method, connection, entityTypeList);
        } else if (type == Update.class) {
            return new DaoUpdateOperation(method, connection, entityTypeList);
        } else if (type == Delete.class) {
            return new DaoDeleteOperation(method, connection, entityTypeList);
        } else {
            throw new DaoMethodException("DaoOperationProvider::createDaoOperation failed unexpectedly."); // Unreachable
        }
    }

    private Class<?> getOperationType(Method method) throws DaoMethodException {
        List<Class<?>> operationTypeList = Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .filter(requiredTypeList::contains)
                .collect(Collectors.toList());
        if (operationTypeList.size() != 1) {
            throw new DaoMethodException("Invalid Dao operation annotations: " + operationTypeList);
        }
        return operationTypeList.get(0);
    }

}
