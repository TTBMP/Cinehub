package com.ttbmp.cinehub.app.utilities.repository;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.InstanceMap;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;

public abstract class JdbcRepository extends Repository {

    private final InstanceMap<Object> daoInstanceMap = new InstanceMap<>();

    protected JdbcRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(Class<T> daoClass) throws RepositoryException {
        try {
            if (daoInstanceMap.get(daoClass) == null) {
                var getter = CinemaDatabase.class.getMethod("get" + daoClass.getSimpleName());
                var dao = (T) getter.invoke(JdbcDataSourceProvider.getDataSource(CinemaDatabase.class));
                daoInstanceMap.put(daoClass, dao);
            }
            return daoInstanceMap.get(daoClass);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
