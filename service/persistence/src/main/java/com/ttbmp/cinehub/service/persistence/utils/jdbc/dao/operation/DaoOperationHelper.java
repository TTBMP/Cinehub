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
        Type[] actualTypeArguments = ((ParameterizedType) genericInputType).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Class<?> genericType = (Class<?>) actualTypeArguments[0];
            if (dataSourceEntityList.contains(genericType)) {
                return genericType;
            }
        }
        throw new DaoMethodException();
    }

    public static List<Method> getDtoSetterList(@NotNull Class<?> dtoType, @NotNull List<String> columnNameList) throws NoSuchMethodException {
        // TODO: columnNameList is probably useless
        List<Method> dtoSetterList = new ArrayList<>();
        Field[] dtoFields = dtoType.getDeclaredFields();
        for (int i = 0; i < dtoFields.length; i++) {
            if (columnNameList.get(i) != null) {
                String fieldName = dtoFields[i].getName();
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                dtoSetterList.add(dtoType.getMethod(setterName, dtoFields[i].getType()));
            }
        }
        return dtoSetterList;
    }

    public static List<Method> getResultSetGetterList(@NotNull Class<?> dtoType, @NotNull List<String> columnNameList) throws NoSuchMethodException {
        List<Method> resultSetGetterList = new ArrayList<>();
        Field[] dtoFields = dtoType.getDeclaredFields();
        for (int i = 0; i < dtoFields.length; i++) {
            if (columnNameList.get(i) != null) {
                String fieldTypeName = dtoFields[i].getType().getSimpleName();
                String getterName = "get" + fieldTypeName.substring(0, 1).toUpperCase() + fieldTypeName.substring(1);
                resultSetGetterList.add(ResultSet.class.getMethod(getterName, String.class));
            }
        }
        return resultSetGetterList;
    }

    public static String getFieldColumnName(@NotNull Field field) {
        ColumnInfo fieldAnnotation = field.getAnnotation(ColumnInfo.class);
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
            Map<String, Field> fieldMap = Arrays.stream(dto.getClass().getDeclaredFields())
                    .filter(f -> columnNameList.contains(f.getAnnotation(ColumnInfo.class).name()))
                    .collect(Collectors.toMap(f -> f.getAnnotation(ColumnInfo.class).name(), f -> f));
            for (String columnName : columnNameList) {
                Field field = fieldMap.get(columnName);
                String fieldName = field.getName();
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method fieldGetMethod = dto.getClass().getMethod(methodName);
                result.put(columnName, fieldGetMethod.invoke(dto));
            }
        }
        return result;
    }

    public static void bindPreparedStatement(@NotNull PreparedStatement statement, @NotNull Map<String, Object> parameterMap, @NotNull List<String> columnNameList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!columnNameList.isEmpty()) {
            Iterator<String> columnNameIterator = columnNameList.listIterator();
            for (int i = 0; i < columnNameList.size(); i++) {
                Object parameter = parameterMap.get(columnNameIterator.next());
                Class<?> typeClass = parameter.getClass();
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
                String typeName = typeClass.getSimpleName();
                String methodName = "set" + typeName.substring(0, 1).toUpperCase() + typeName.substring(1);
                Method statementSetMethod = PreparedStatement.class.getMethod(methodName, int.class, typeClass);
                statementSetMethod.invoke(statement, i + 1, parameter);
            }
        }
    }

}
