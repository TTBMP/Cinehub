package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperationHelper;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoDeleteOperation extends DaoOperation {

    private final List<String> entityPrimaryKeyColumnNameList;

    public DaoDeleteOperation(Method method, Connection connection, List<Class<?>> entityTypeList) throws DaoMethodException {
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
        entityPrimaryKeyColumnNameList = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> f.getAnnotation(PrimaryKey.class) != null)
                .map(f -> f.getAnnotation(ColumnInfo.class).name())
                .collect(Collectors.toList());
        queryTemplate = String.format(
                "DELETE FROM %s WHERE (%s = ?)",
                entityType.getAnnotation(Entity.class).tableName(),
                String.join(" = ?, ", entityPrimaryKeyColumnNameList)
        );
    }

    @Override
    public Object execute(Object[] args) throws DaoMethodException {
        // foreign key delete behaviour should be handled here
        try (var statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            if (requireCollection) {
                for (Object entity : (List<?>) args[0]) {
                    DaoOperationHelper.bindPreparedStatement(
                            statement,
                            DaoOperationHelper.getStatementParameterMap(entity, entityPrimaryKeyColumnNameList),
                            entityPrimaryKeyColumnNameList
                    );
                    statement.addBatch();
                }
                statement.executeBatch();
            } else {
                var entity = args[0];
                DaoOperationHelper.bindPreparedStatement(
                        statement,
                        DaoOperationHelper.getStatementParameterMap(entity, entityPrimaryKeyColumnNameList),
                        entityPrimaryKeyColumnNameList
                );
                statement.executeUpdate();
            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new DaoMethodException(e.getMessage());
        }
        return null;
    }

}
