package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoOperationHelper {

    private DaoOperationHelper() {

    }

    public static Class<?> getDtoType(@NotNull Class<?> objectType, @NotNull Type genericInputType, List<Class<?>> dataSourceEntityList) throws DaoMethodException {
        if (dataSourceEntityList.contains(objectType)) {
            return objectType;
        }
        if (List.class.isAssignableFrom(objectType)) {
            return getActualDtoType(genericInputType, dataSourceEntityList);
        }
        throw new DaoMethodException();
    }

    private static Class<?> getActualDtoType(@NotNull Type genericInputType, List<Class<?>> dataSourceEntityList) throws DaoMethodException {
        var actualTypeArguments = ((ParameterizedType) genericInputType).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            var genericType = (Class<?>) actualTypeArguments[0];
            if (dataSourceEntityList.contains(genericType)) {
                return genericType;
            }
        }
        throw new DaoMethodException();
    }

    public static List<Method> getDtoSetterList(@NotNull Class<?> dtoType, @NotNull List<String> columnNameList) throws NoSuchMethodException {
        // TODO: columnNameList is probably useless
        List<Method> dtoSetterList = new ArrayList<>();
        var dtoFields = dtoType.getDeclaredFields();
        for (var i = 0; i < dtoFields.length; i++) {
            if (columnNameList.get(i) != null) {
                var fieldName = dtoFields[i].getName();
                var setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                dtoSetterList.add(dtoType.getMethod(setterName, dtoFields[i].getType()));
            }
        }
        return dtoSetterList;
    }

    public static List<Method> getResultSetGetterList(@NotNull Class<?> dtoType, @NotNull List<String> columnNameList) throws NoSuchMethodException {
        List<Method> resultSetGetterList = new ArrayList<>();
        var dtoFields = dtoType.getDeclaredFields();
        for (var i = 0; i < dtoFields.length; i++) {
            if (columnNameList.get(i) != null) {
                var fieldTypeName = dtoFields[i].getType().getSimpleName();
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

    // TODO: find a better name
    public static <T> Map<String, Object> getParameterMap(@NotNull T dto, @NotNull List<String> columnNameList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, Object> result = new HashMap<>();
        if (!columnNameList.isEmpty()) {
            var fieldMap = Arrays.stream(dto.getClass().getDeclaredFields())
                    .filter(f -> columnNameList.contains(f.getAnnotation(ColumnInfo.class).name()))
                    .collect(Collectors.toMap(f -> f.getAnnotation(ColumnInfo.class).name(), f -> f));
            for (var columnName : columnNameList) {
                var field = fieldMap.get(columnName);
                var fieldName = field.getName();
                var methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                var fieldGetMethod = dto.getClass().getMethod(methodName);
                result.put(columnName, fieldGetMethod.invoke(dto));
            }
        }
        return result;
    }

    public static void bindPreparedStatement(@NotNull PreparedStatement statement, @NotNull Map<String, Object> parameterMap, @NotNull List<String> columnNameList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!columnNameList.isEmpty()) {
            Iterator<String> columnNameIterator = columnNameList.listIterator();
            for (int i = 0; i < columnNameList.size(); i++) {
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

}
