package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperation;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.DaoOperationHelper;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.persistence.NoResultException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Query queryAnnotation = method.getAnnotation(Query.class);
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
    public Object execute(Object[] args) throws DaoMethodException{
        Object result;
        try (PreparedStatement statement = connection.prepareStatement(
                queryTemplate,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            DaoOperationHelper.bindPreparedStatement(
                    statement,
                    getQueryTemplateParameterNameValueMap(args),
                    queryTemplateParameterNameList
            );
            ResultSet resultSet = statement.executeQuery();
            result = getResultObject(resultSet);
            resultSet.close();
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |NoResultException e) {
            throw new DaoMethodException();
        }
        return result;
    }

    private Map<String, Object> getQueryTemplateParameterNameValueMap(Object[] args) throws DaoMethodException {
        Map<String, Object> result = new HashMap<>();
        if (args != null) {
            Iterator<Object> parameterIterator = Arrays.stream(args).iterator();
            for (Annotation[] parameterAnnotation : method.getParameterAnnotations()) {
                boolean firstParameterAnnotation = true;
                for (Annotation annotation : parameterAnnotation) {
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

    private Object getResultObject(ResultSet resultSet) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoResultException {
        Object result;
        if (List.class.isAssignableFrom(objectType)) {
            result = objectType.cast(new ArrayList<>());
        } else {
            result = objectType.cast(objectType.getConstructors()[0].newInstance());
        }
        if (!resultSet.first()) {
            throw new NoResultException();
        }
        List<Method> resultSetGetterList = DaoOperationHelper.getResultSetGetterList(dtoType, dtoColumnNameList);
        dtoColumnNameList.removeIf(Objects::isNull);
        do {
            Iterator<String> columnNameIterator = dtoColumnNameList.listIterator();
            Iterator<Method> resultSetGetterIterator = resultSetGetterList.listIterator();
            if (List.class.isAssignableFrom(objectType)) {
                Object dto = dtoType.getConstructors()[0].newInstance();
                for (Method dtoSetter : dtoSetterList) {
                    dtoSetter.invoke(dto, resultSetGetterIterator.next().invoke(resultSet, columnNameIterator.next()));
                }
                List.class.getMethod("add", Object.class).invoke(result, dto);
            } else {
                for (Method dtoSetter : dtoSetterList) {
                    dtoSetter.invoke(result, resultSetGetterIterator.next().invoke(resultSet, columnNameIterator.next()));
                }
            }
        } while (resultSet.next());
        return result;
    }
}
