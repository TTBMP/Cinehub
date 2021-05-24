package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.UsherDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcUsherRepository implements UsherRepository {

    private final ServiceLocator serviceLocator;

    private UsherDao usherDao = null;

    public JdbcUsherRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Usher getUsher(UsherShift usherShift) throws RepositoryException {
        try {
            var employee = getUsherDao().getUsherByUsherShift(usherShift.getId());
            return new UsherProxy(serviceLocator, employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private UsherDao getUsherDao() throws RepositoryException {
        if (usherDao == null) {
            try {
                this.usherDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUsherDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return usherDao;
    }

}

