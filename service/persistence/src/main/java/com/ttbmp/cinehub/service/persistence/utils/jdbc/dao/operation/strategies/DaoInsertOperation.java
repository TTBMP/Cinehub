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

    private final List<String> entityColumnNameList;

    public DaoInsertOperation(Method method, Connection connection, List<Class<?>> entityTypeList) throws DaoMethodException {
        super(method, connection, entityTypeList);
        if (!method.getReturnType().equals(Void.TYPE) || method.getParameterCount() != 1) {
            throw new DaoMethodException("Invalid Dao method declaration.");
        }
        requireCollection = List.class.isAssignableFrom(method.getParameterTypes()[0]);
        if (requireCollection) {
            entityType = DaoOperationHelper.getEntityType(method.getGenericParameterTypes()[0], entityTypeList);
        } else {
            entityType = DaoOperationHelper.getEntityType(method.getParameterTypes()[0], entityTypeList);
        }
        entityColumnNameList = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> !(f.getAnnotation(PrimaryKey.class) != null && f.getAnnotation(PrimaryKey.class).autoGenerate()))
                .filter(f -> f.getAnnotation(Ignore.class) == null)
                .map(f -> f.getAnnotation(ColumnInfo.class).name())
                .collect(Collectors.toList());
        queryTemplate = getQueryTemplate();
    }

    @Override
    public Object execute(Object[] args) throws DaoMethodException {
        try (var statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            if (requireCollection) {
                for (Object entity : (List<?>) args[0]) {
                    DaoOperationHelper.bindPreparedStatement(
                            statement,
                            DaoOperationHelper.getStatementParameterMap(entity, entityColumnNameList),
                            entityColumnNameList
                    );
                    statement.addBatch();
                }
                statement.executeBatch();
            } else {
                var entity = args[0];
                DaoOperationHelper.bindPreparedStatement(
                        statement,
                        DaoOperationHelper.getStatementParameterMap(entity, entityColumnNameList),
                        entityColumnNameList
                );
                statement.executeUpdate();
            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new DaoMethodException(e.getMessage());
        }
        return null;
    }

    private String getQueryTemplate() throws DaoMethodException {
        var entityTableName = entityType.getAnnotation(Entity.class).tableName();
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
                throw new DaoMethodException("Invalid OnConflictStrategy.");
        }
        return String.format(
                "%s%s (%s) VALUES (%s)",
                expression,
                entityTableName,
                String.join(", ", entityColumnNameList),
                String.join(", ", Collections.nCopies(entityColumnNameList.size(), "?"))
        );
    }

}
