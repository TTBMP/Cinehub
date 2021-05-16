package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionistShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcProjectionistShiftRepository implements ProjectionistShiftRepository {

    private final ServiceLocator serviceLocator;

    private ProjectionistShiftDao projectionistShiftDao = null;

    public JdbcProjectionistShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public ProjectionistShift getProjectionistShift(int shiftId) throws RepositoryException {
        var shift = serviceLocator.getService(ShiftRepository.class).getShift(shiftId);
        return new ProjectionistShiftProxy(
                shift.getId(),
                shift.getDate(),
                shift.getStart(),
                shift.getEnd(),
                serviceLocator.getService(ProjectionistRepository.class),
                serviceLocator.getService(HallRepository.class),
                serviceLocator.getService(ProjectionRepository.class)
        );
    }

    @Override
    public void saveShift(ProjectionistShift shift) throws RepositoryException {
        try {
            var shiftDto = new com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift(shift.getId(), shift.getHall().getId());
            getProjectionistShiftDao().insert(shiftDto);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void modifyShift(ProjectionistShift shift) throws RepositoryException {
        try {
            serviceLocator.getService(ShiftRepository.class).modifyShift(shift);
            var shiftDto = new com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift(shift.getId(), shift.getHall().getId());
            getProjectionistShiftDao().update(shiftDto);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private ProjectionistShiftDao getProjectionistShiftDao() throws RepositoryException {
        if (projectionistShiftDao == null) {
            try {
                this.projectionistShiftDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionistShiftDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return projectionistShiftDao;
    }

}

