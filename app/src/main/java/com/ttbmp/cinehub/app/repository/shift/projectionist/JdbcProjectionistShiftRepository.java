package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionistShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcProjectionistShiftRepository extends JdbcRepository implements ProjectionistShiftRepository {

    public JdbcProjectionistShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public ProjectionistShift getProjectionistShift(int shiftId) throws RepositoryException {
        var shift = getServiceLocator().getService(ShiftRepository.class).getShift(shiftId);
        return new ProjectionistShiftProxy(getServiceLocator(), shift.getId(), shift.getDate(), shift.getStart(), shift.getEnd());
    }

    @Override
    public void saveShift(ProjectionistShift shift) throws RepositoryException {
        try {
            var shiftDto = new com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift(shift.getId(), shift.getHall().getId());
            getDao(ProjectionistShiftDao.class).insert(shiftDto);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void modifyShift(ProjectionistShift shift) throws RepositoryException {
        try {
            getServiceLocator().getService(ShiftRepository.class).modifyShift(shift);
            var shiftDto = new com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift(shift.getId(), shift.getHall().getId());
            getDao(ProjectionistShiftDao.class).update(shiftDto);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
