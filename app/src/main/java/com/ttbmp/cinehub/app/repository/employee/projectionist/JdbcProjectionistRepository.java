package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionistDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcProjectionistRepository implements ProjectionistRepository {

    private final ServiceLocator serviceLocator;

    private ProjectionistDao projectionistDao = null;


    public JdbcProjectionistRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Projectionist getProjectionist(Projection projection) throws RepositoryException {
        try {
            var employee = getProjectionistDao().getProjectionistByProjectionId(projection.getId());
            return new ProjectionistProxy(serviceLocator, employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Projectionist getProjectionist(ProjectionistShift projectionistShift) throws RepositoryException {
        try {
            var employee = getProjectionistDao().getProjectionistByProjectionistShift(projectionistShift.getId());
            return new ProjectionistProxy(serviceLocator, employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private ProjectionistDao getProjectionistDao() throws RepositoryException {
        if (projectionistDao == null) {
            try {
                this.projectionistDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionistDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return projectionistDao;
    }

}
