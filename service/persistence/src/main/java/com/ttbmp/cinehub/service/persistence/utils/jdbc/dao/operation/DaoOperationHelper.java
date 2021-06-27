package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoOperationHelper {

    private DaoOperationHelper() {

    }

    public static Class<?> getEntityType(@NotNull Class<?> requiredType, @NotNull List<Class<?>> entityTypeList) throws DaoMethodException {
        if (entityTypeList.contains(requiredType)) {
            return requiredType;
        }
        throw new DaoMethodException(requiredType + " is not contained into datasource entities.");
    }

    public static Class<?> getEntityType(@NotNull Type requiredGenericType, @NotNull List<Class<?>> entityTypeList) throws DaoMethodException {
        var actualTypeArguments = ((ParameterizedType) requiredGenericType).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            var requiredType = (Class<?>) actualTypeArguments[0];
            if (entityTypeList.contains(requiredType)) {
                return requiredType;
            }
        }
        throw new DaoMethodException(Arrays.toString(actualTypeArguments) + " not contained into datasource entities.");
    }

    public static List<Method> getEntitySetterList(@NotNull Class<?> entityType) throws NoSuchMethodException {
        var columnNameList = Arrays.stream(entityType.getDeclaredFields())
                .map(DaoOperationHelper::getFieldColumnName)
                .collect(Collectors.toList());
        var entityFields = entityType.getDeclaredFields();
        List<Method> entitySetterList = new ArrayList<>();
        for (var i = 0; i < entityFields.length; i++) {
            if (columnNameList.get(i) != null) {
                var fieldName = entityFields[i].getName();
                var setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                entitySetterList.add(entityType.getMethod(setterName, entityFields[i].getType()));
            }
        }
        return entitySetterList;
    }

    public static List<Method> getResultSetGetterList(@NotNull Class<?> entityType, @NotNull List<String> columnNameList) throws NoSuchMethodException {
        List<Method> resultSetGetterList = new ArrayList<>();
        var entityFields = entityType.getDeclaredFields();
        for (var i = 0; i < entityFields.length; i++) {
            if (columnNameList.get(i) != null) {
                var fieldTypeName = entityFields[i].getType().getSimpleName();
                var getterName = "get" + fieldTypeName.substring(0, 1).toUpperCase() + fieldTypeName.substring(1);
                resultSetGetterList.add(ResultSet.class.getMethod(getterName, String.class));
            }
        }
        return resultSetGetterList;
    }

    public static String getFieldColumnName(@NotNull Field field) {
        var fieldAnnotation = field.getAnnotation(ColumnInfo.class);
        if (fieldAnnotation == null) {
            return null;
        } else if (fieldAnnotation.name().equals("[field-name]")) {
            return field.getName();
        }
        return fieldAnnotation.name();
    }

    public static <T> Map<String, Object> getStatementParameterMap(@NotNull T entity, @NotNull List<String> columnNameList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, Object> result = new HashMap<>();
        if (!columnNameList.isEmpty()) {
            var fieldMap = Arrays.stream(entity.getClass().getDeclaredFields())
                    .filter(f -> columnNameList.contains(f.getAnnotation(ColumnInfo.class).name()))
                    .collect(Collectors.toMap(f -> f.getAnnotation(ColumnInfo.class).name(), f -> f));
            for (var columnName : columnNameList) {
                var field = fieldMap.get(columnName);
                var fieldName = field.getName();
                var methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                var fieldGetMethod = entity.getClass().getMethod(methodName);
                result.put(columnName, fieldGetMethod.invoke(entity));
            }
        }
        return result;
    }

    public static void bindPreparedStatement(@NotNull PreparedStatement statement, @NotNull Map<String, Object> parameterMap, @NotNull List<String> columnNameList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!columnNameList.isEmpty()) {
            Iterator<String> columnNameIterator = columnNameList.listIterator();
            for (var i = 0; i < columnNameList.size(); i++) {
                var parameter = parameterMap.get(columnNameIterator.next());
                var typeClass = parameter.getClass();
                switch (typeClass.getName()) {
                    case "java.lang.Boolean":
                        typeClass = boolean.class;
                        break;
                    case "java.lang.Byte":
                        typeClass = byte.class;
                        break;
                    case "java.lang.Short":
                        typeClass = short.class;
                        break;
                    case "java.lang.Integer":
                        typeClass = int.class;
                        break;
                    case "java.lang.Long":
                        typeClass = long.class;
                        break;
                    case "java.lang.Float":
                        typeClass = float.class;
                        break;
                    case "java.lang.Double":
                        typeClass = double.class;
                        break;
                    default:
                        break;
                }
                var typeName = typeClass.getSimpleName();
                var methodName = "set" + typeName.substring(0, 1).toUpperCase() + typeName.substring(1);
                var statementSetMethod = PreparedStatement.class.getMethod(methodName, int.class, typeClass);
                statementSetMethod.invoke(statement, i + 1, parameter);
            }
        }
    }

    public static boolean resultSetHasNext(ResultSet resultSet) throws SQLException {
        var result = resultSet.next();
        resultSet.previous();
        return result;
    }

}
