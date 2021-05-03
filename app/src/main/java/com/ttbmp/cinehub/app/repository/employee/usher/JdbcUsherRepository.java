package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
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

            return new UsherProxy(
                    employee.getIdUser(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(CinemaRepository.class),
                    serviceLocator.getService(ShiftRepository.class)
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private UsherDao getUsherDao() {
        if (usherDao == null) {
            try {
                this.usherDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUsherDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usherDao;
    }

}

