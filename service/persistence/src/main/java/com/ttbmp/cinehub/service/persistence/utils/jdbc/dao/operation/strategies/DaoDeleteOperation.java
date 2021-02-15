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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoDeleteOperation extends DaoOperation {

    private final List<String> dtoPrimaryKeyColumnNameList;

    public DaoDeleteOperation(Method method, Connection connection, List<Class<?>> dataSourceEntityList) throws DaoMethodException {
        super(method, connection, dataSourceEntityList);
        if (!method.getReturnType().equals(Void.TYPE) || method.getParameterCount() != 1) {
            throw new DaoMethodException();
        }
        objectType = method.getParameterTypes()[0];
        dtoType = DaoOperationHelper.getDtoType(objectType, method.getGenericParameterTypes()[0], dataSourceEntityList);
        dtoPrimaryKeyColumnNameList = Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> f.getAnnotation(PrimaryKey.class) != null)
                .map(f -> f.getAnnotation(ColumnInfo.class).name())
                .collect(Collectors.toList());
        queryTemplate = String.format(
                "DELETE FROM %s WHERE (%s = ?)",
                dtoType.getAnnotation(Entity.class).tableName(),
                String.join(" = ?, ", dtoPrimaryKeyColumnNameList)
        );
    }

    @Override
    public Object execute(Object[] args) throws InvocationTargetException, SQLException, NoSuchMethodException, IllegalAccessException {

        try (PreparedStatement statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            if (List.class.isAssignableFrom(objectType)) {
                for (Object dto : (List<?>) args[0]) {
                    DaoOperationHelper.bindPreparedStatement(
                            statement,
                            DaoOperationHelper.getParameterMap(dto, dtoPrimaryKeyColumnNameList),
                            dtoPrimaryKeyColumnNameList
                    );
                    statement.addBatch();
                }
                statement.executeBatch();
            } else {
                Object dto = args[0];
                DaoOperationHelper.bindPreparedStatement(
                        statement,
                        DaoOperationHelper.getParameterMap(dto, dtoPrimaryKeyColumnNameList),
                        dtoPrimaryKeyColumnNameList
                );
                statement.executeUpdate();
            }
        }
        return null;
    }

}
