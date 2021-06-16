package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.dao.HallDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcHallRepository extends JdbcRepository implements HallRepository {

    public JdbcHallRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public List<Hall> getHallList(Cinema cinema) throws RepositoryException {
        try {
            var hallList = getDao(HallDao.class).getHallByCinemaId(cinema.getId());
            return hallList.stream()
                    .map(hall -> new HallProxy(getServiceLocator(), hall.getId(), hall.getNumber()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Hall getHall(Projection projection) throws RepositoryException {
        try {
            var hall = getDao(HallDao.class).getHallByProjectionId(projection.getId());
            return new HallProxy(getServiceLocator(), hall.getId(), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Hall getHall(ProjectionistShift projectionistShift) throws RepositoryException {
        try {
            var hall = getDao(HallDao.class).getHallByProjectionistShift(projectionistShift.getId());
            return new HallProxy(getServiceLocator(), hall.getId(), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Hall getHall(int hallId) throws RepositoryException {
        try {
            var hall = getDao(HallDao.class).getHallById(hallId);
            if (hall == null) {
                return null;
            }
            return new HallProxy(getServiceLocator(), hall.getId(), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
