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

    private final int updateStrategy;
    private final List<String> dtoColumnNameList;
    private final List<String> dtoPrimaryKeyColumnNameList;

    public DaoUpdateOperation(Method method, Connection connection, List<Class<?>> dataSourceEntityList) throws DaoMethodException {
        super(method, connection, dataSourceEntityList);
        if (!method.getReturnType().equals(Void.TYPE) || method.getParameterCount() != 1) {
            throw new DaoMethodException("Invalid Dao method declaration.");
        }
        var updateAnnotation = method.getAnnotation(Update.class);
        updateStrategy = updateAnnotation.onConflict();
        objectType = method.getParameterTypes()[0];
        dtoType = DaoOperationHelper.getDtoType(objectType, method.getGenericParameterTypes()[0], dataSourceEntityList);
        dtoColumnNameList = Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> !(f.getAnnotation(PrimaryKey.class) != null && f.getAnnotation(PrimaryKey.class).autoGenerate()))
                .filter(f -> f.getAnnotation(Ignore.class) == null)
                .map(f -> f.getAnnotation(ColumnInfo.class).name())
                .collect(Collectors.toList());
        dtoPrimaryKeyColumnNameList = Arrays.stream(dtoType.getDeclaredFields())
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
            if (List.class.isAssignableFrom(objectType)) {
                for (Object dto : (List<?>) args[0]) {
                    if (updateStrategy == OnConflictStrategy.ABORT || updateStrategy == OnConflictStrategy.IGNORE) {
                        dtoColumnNameList.addAll(dtoPrimaryKeyColumnNameList.stream()
                                .filter(t -> dtoColumnNameList.stream().noneMatch(t::equals))
                                .collect(Collectors.toList()));
                    }
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
                if (updateStrategy == OnConflictStrategy.ABORT || updateStrategy == OnConflictStrategy.IGNORE) {
                    dtoColumnNameList.addAll(dtoPrimaryKeyColumnNameList);
                }
                DaoOperationHelper.bindPreparedStatement(
                        statement,
                        DaoOperationHelper.getParameterMap(dto, dtoColumnNameList),
                        dtoColumnNameList
                );
                statement.executeUpdate();
            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new DaoMethodException(e.getMessage());
        }
        return null;
    }

    private String getQueryTemplate() throws DaoMethodException {
        var dtoTableName = dtoType.getAnnotation(Entity.class).tableName();
        String query;
        switch (updateStrategy) {
            case OnConflictStrategy.ABORT:
                query = String.format(
                        "UPDATE %s SET %s = ? WHERE %s = ?",
                        dtoTableName,
                        String.join(" = ?, ", dtoColumnNameList),
                        String.join(" = ?, ", dtoPrimaryKeyColumnNameList)
                );
                break;
            case OnConflictStrategy.IGNORE:
                query = String.format(
                        "UPDATE IGNORE %s SET %s = ? WHERE %s = ?",
                        dtoTableName,
                        String.join(" = ?, ", dtoColumnNameList),
                        String.join(" = ?, ", dtoPrimaryKeyColumnNameList)
                );
                break;
            case OnConflictStrategy.REPLACE:
                query = String.format(
                        "REPLACE INTO %s (%s) VALUES (%s)",
                        dtoTableName,
                        String.join(", ", dtoColumnNameList),
                        String.join(", ", Collections.nCopies(dtoColumnNameList.size(), "?"))
                );
                break;
            default:
                throw new DaoMethodException("Invalid OnConflictStrategy Dao update method.");
        }
        return query;
    }

}
