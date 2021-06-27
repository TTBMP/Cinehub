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
public class DaoUpdateOperation extends DaoOperation {

    private static final String PREPARED_STATEMENT_DELIMITER = " = ?, ";

    private final int updateStrategy;
    private final List<String> entityColumnNameList;
    private final List<String> entityPrimaryKeyColumnNameList;

    public DaoUpdateOperation(Method method, Connection connection, List<Class<?>> entityTypeList) throws DaoMethodException {
        super(method, connection, entityTypeList);
        if (!method.getReturnType().equals(Void.TYPE) || method.getParameterCount() != 1) {
            throw new DaoMethodException("Invalid Dao method declaration.");
        }
        var updateAnnotation = method.getAnnotation(Update.class);
        updateStrategy = updateAnnotation.onConflict();
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
        entityPrimaryKeyColumnNameList = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> f.getAnnotation(PrimaryKey.class) != null)
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
                    if (updateStrategy == OnConflictStrategy.ABORT || updateStrategy == OnConflictStrategy.IGNORE) {
                        entityColumnNameList.addAll(entityPrimaryKeyColumnNameList.stream()
                                .filter(t -> entityColumnNameList.stream().noneMatch(t::equals))
                                .collect(Collectors.toList()));
                    }
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
                if (updateStrategy == OnConflictStrategy.ABORT || updateStrategy == OnConflictStrategy.IGNORE) {
                    entityColumnNameList.addAll(entityPrimaryKeyColumnNameList);
                }
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
        String query;
        switch (updateStrategy) {
            case OnConflictStrategy.ABORT:
                query = String.format(
                        "UPDATE %s SET %s = ? WHERE %s = ?",
                        entityTableName,
                        String.join(PREPARED_STATEMENT_DELIMITER, entityColumnNameList),
                        String.join(PREPARED_STATEMENT_DELIMITER, entityPrimaryKeyColumnNameList)
                );
                break;
            case OnConflictStrategy.IGNORE:
                query = String.format(
                        "UPDATE IGNORE %s SET %s = ? WHERE %s = ?",
                        entityTableName,
                        String.join(PREPARED_STATEMENT_DELIMITER, entityColumnNameList),
                        String.join(PREPARED_STATEMENT_DELIMITER, entityPrimaryKeyColumnNameList)
                );
                break;
            case OnConflictStrategy.REPLACE:
                query = String.format(
                        "REPLACE INTO %s (%s) VALUES (%s)",
                        entityTableName,
                        String.join(", ", entityColumnNameList),
                        String.join(", ", Collections.nCopies(entityColumnNameList.size(), "?"))
                );
                break;
            default:
                throw new DaoMethodException("Invalid OnConflictStrategy Dao update method.");
        }
        return query;
    }

}
