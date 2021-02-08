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

    private final Map<Method, DaoOperation> operationInstanceMap = new HashMap<>();
    private final List<Class<?>> requiredTypeList = Arrays.asList(
            Query.class,
            Insert.class,
            Update.class,
            Delete.class
    );

    public DaoOperation getDaoOperation(@NotNull Method method, @NotNull Connection connection,
                                        List<Class<?>> dataSourceEntityList) throws DaoMethodException, NoSuchMethodException {

        operationInstanceMap.putIfAbsent(method, createDaoOperation(method, connection, dataSourceEntityList));
        return operationInstanceMap.get(method);
    }

    private DaoOperation createDaoOperation(@NotNull Method method, @NotNull Connection connection,
                                            List<Class<?>> dataSourceEntityList) throws DaoMethodException, NoSuchMethodException {
        Type type = getDaoOperationType(method.getAnnotations());
        if (type == Query.class) {
            return new DaoQueryOperation(method, connection, dataSourceEntityList);
        } else if (type == Insert.class) {
            return new DaoInsertOperation(method, connection, dataSourceEntityList);
        } else if (type == Update.class) {
            return new DaoUpdateOperation(method, connection, dataSourceEntityList);
        } else if (type == Delete.class) {
            return new DaoDeleteOperation(method, connection, dataSourceEntityList);
        } else {
            throw new DaoMethodException(); // Unreachable
        }
    }

    private Class<?> getDaoOperationType(Annotation[] annotations) throws DaoMethodException {
        List<Class<?>> filteredAnnotationTypeList = Arrays.stream(annotations)
                .map(Annotation::annotationType)
                .filter(requiredTypeList::contains)
                .collect(Collectors.toList());
        if (filteredAnnotationTypeList.size() != 1) {
            throw new DaoMethodException();
        }
        return filteredAnnotationTypeList.iterator().next();
    }

}
