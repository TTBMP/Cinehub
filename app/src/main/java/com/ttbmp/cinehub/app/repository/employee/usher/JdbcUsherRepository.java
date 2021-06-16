package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;
import com.ttbmp.cinehub.service.persistence.dao.UsherDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcUsherRepository extends JdbcRepository implements UsherRepository {

    public JdbcUsherRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Usher getUsher(UsherShift usherShift) throws RepositoryException {
        try {
            var employee = getDao(UsherDao.class).getUsherByUsherShift(usherShift.getId());
            return new UsherProxy(getServiceLocator(), employee.getIdUser());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}

