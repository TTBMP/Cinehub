package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperationHelper;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DaoQueryOperation extends DaoOperation {

    private final List<String> queryTemplateParameterNameList;
    private final List<String> dtoColumnNameList;
    private final List<Method> dtoSetterList;

    public DaoQueryOperation(Method method, Connection connection, List<Class<?>> dataSourceEntityList) throws DaoMethodException, NoSuchMethodException {
        super(method, connection, dataSourceEntityList);
        var queryAnnotation = method.getAnnotation(Query.class);
        objectType = method.getReturnType();
        dtoType = DaoOperationHelper.getDtoType(objectType, method.getGenericReturnType(), dataSourceEntityList);
        queryTemplate = queryAnnotation.value().replaceAll(":[a-zA-Z_$][a-zA-Z_$0-9]*", "?");
        queryTemplateParameterNameList = Pattern.compile("(?=(:[a-zA-Z_$][a-zA-Z_$0-9]*))")
                .matcher(queryAnnotation.value())
                .results()
                .map(matchResult -> matchResult.group(1).substring(1))
                .collect(Collectors.toList());
        dtoColumnNameList = Arrays.stream(dtoType.getDeclaredFields())
                .map(DaoOperationHelper::getFieldColumnName)
                .collect(Collectors.toList());
        dtoSetterList = DaoOperationHelper.getDtoSetterList(dtoType, dtoColumnNameList);
    }

    @Override
    public Object execute(Object[] args) throws DaoMethodException {
        try (var statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            DaoOperationHelper.bindPreparedStatement(
                    statement,
                    getQueryTemplateParameterNameValueMap(args),
                    queryTemplateParameterNameList
            );
            return getResult(statement.executeQuery());
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new DaoMethodException();
        }
    }

    private Map<String, Object> getQueryTemplateParameterNameValueMap(Object[] args) throws DaoMethodException {
        Map<String, Object> result = new HashMap<>();
        if (args != null) {
            var parameterIterator = Arrays.stream(args).iterator();
            for (var parameterAnnotation : method.getParameterAnnotations()) {
                var firstParameterAnnotation = true;
                for (var annotation : parameterAnnotation) {
                    if (annotation instanceof Parameter) {
                        if (!firstParameterAnnotation) {
                            throw new DaoMethodException();
                        }
                        result.put(((Parameter) annotation).name(), parameterIterator.next());
                        firstParameterAnnotation = false;
                    }
                }
                if (firstParameterAnnotation) {
                    throw new DaoMethodException();
                }
            }
        }
        return result;
    }

    private Object getResult(ResultSet resultSet) throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object result;
        var resultSetGetterList = DaoOperationHelper.getResultSetGetterList(dtoType, dtoColumnNameList);
        dtoColumnNameList.removeIf(Objects::isNull);
        if (List.class.isAssignableFrom(objectType)) {
            result = getResultList(resultSet, resultSetGetterList);
        } else {
            result = getResultObject(resultSet, resultSetGetterList);
        }
        resultSet.close();
        return result;
    }

    private List<Object> getResultList(ResultSet resultSet, List<Method> resultSetGetterList) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Object> result = new ArrayList<>();
        while (DaoOperationHelper.resultSetHasNext(resultSet)) {
            result.add(getResultObject(resultSet, resultSetGetterList));
        }
        return result;
    }

    private Object getResultObject(ResultSet resultSet, List<Method> resultSetGetterList) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object result = null;
        if (resultSet.next()) {
            result = dtoType.getConstructor().newInstance();
            for (var i = 0; i < dtoSetterList.size(); i++) {
                var value = resultSetGetterList.get(i).invoke(resultSet, dtoColumnNameList.get(i));
                var setter = dtoSetterList.get(i);
                setter.invoke(result, value);
            }
        }
        // throw a NoResultException if the Single<T> class is used in an else branch
        return result;
    }

}
