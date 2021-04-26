package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperationHelper;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoInsertOperation extends DaoOperation {

    private final List<String> dtoColumnNameList;

    public DaoInsertOperation(Method method, Connection connection, List<Class<?>> dataSourceEntityList) throws DaoMethodException {
        super(method, connection, dataSourceEntityList);
        if (!method.getReturnType().equals(Void.TYPE) || method.getParameterCount() != 1) {
            throw new DaoMethodException();
        }
        objectType = method.getParameterTypes()[0];
        dtoType = DaoOperationHelper.getDtoType(objectType, method.getGenericParameterTypes()[0], dataSourceEntityList);
        dtoColumnNameList = Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> !(f.getAnnotation(PrimaryKey.class) != null && f.getAnnotation(PrimaryKey.class).autoGenerate()))
                .filter(f -> f.getAnnotation(Ignore.class) == null)
                .map(f -> f.getAnnotation(ColumnInfo.class).name())
                .collect(Collectors.toList());
        queryTemplate = getQueryTemplate();
    }

    @Override
    public Object execute(Object[] args) throws DaoMethodException, InvocationTargetException, SQLException, NoSuchMethodException, IllegalAccessException {
        try (var statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            if (List.class.isAssignableFrom(objectType)) {
                for (Object dto : (List<?>) args[0]) {
                    DaoOperationHelper.bindPreparedStatement(
                            statement,
                            DaoOperationHelper.getParameterMap(dto, dtoColumnNameList),
                            dtoColumnNameList
                    );
                    statement.addBatch();
                }
                statement.executeBatch();
            } else {
                var dto = args[0];
                DaoOperationHelper.bindPreparedStatement(
                        statement,
                        DaoOperationHelper.getParameterMap(dto, dtoColumnNameList),
                        dtoColumnNameList
                );
                statement.executeUpdate();
            }
        }
        return null;
    }

    private String getQueryTemplate() throws DaoMethodException {
        var dtoTableName = dtoType.getAnnotation(Entity.class).tableName();
        var insertStrategy = method.getAnnotation(Insert.class).onConflict();
        String expression;
        switch (insertStrategy) {
            case OnConflictStrategy.REPLACE:
                expression = "REPLACE INTO ";
                break;
            case OnConflictStrategy.ABORT:
                expression = "INSERT INTO ";
                break;
            case OnConflictStrategy.IGNORE:
                expression = "INSERT IGNORE INTO ";
                break;
            default:
                throw new DaoMethodException();
        }
        return String.format(
                "%s%s (%s) VALUES (%s)",
                expression,
                dtoTableName,
                String.join(", ", dtoColumnNameList),
                String.join(", ", Collections.nCopies(dtoColumnNameList.size(), "?"))
        );
    }

}
