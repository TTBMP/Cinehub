package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionistDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcProjectionistRepository extends JdbcRepository implements ProjectionistRepository {

    public JdbcProjectionistRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Projectionist getProjectionist(Projection projection) throws RepositoryException {
        try {
            var employee = getDao(ProjectionistDao.class).getProjectionistByProjectionId(projection.getId());
            return new ProjectionistProxy(getServiceLocator(), employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Projectionist getProjectionist(ProjectionistShift projectionistShift) throws RepositoryException {
        try {
            var employee = getDao(EmployeeDao.class).getEmployeeByShiftId(projectionistShift.getId());
            return new ProjectionistProxy(getServiceLocator(), employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
