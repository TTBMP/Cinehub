package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.HallDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcHallRepository implements HallRepository {

    private final ServiceLocator serviceLocator;

    private HallDao hallDao = null;

    public JdbcHallRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public List<Hall> getHallList(Cinema cinema) throws RepositoryException {
        try {
            var hallList = getHallDao().getHallByCinemaId(cinema.getId());
            return hallList.stream()
                    .map(hall -> new HallProxy(hall.getId(), serviceLocator.getService(SeatRepository.class), hall.getNumber()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Hall getHall(Projection projection) throws RepositoryException {
        try {
            var hall = getHallDao().getHallByProjectionId(projection.getId());
            return new HallProxy(hall.getId(), serviceLocator.getService(SeatRepository.class), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }


    }

    @Override
    public Hall getHall(ProjectionistShift projectionistShift) throws RepositoryException {
        try {
            var hall = getHallDao().getHallByProjectionistShift(projectionistShift.getId());
            return new HallProxy(hall.getId(), serviceLocator.getService(SeatRepository.class), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }


    }

    @Override
    public Hall getHall(int hallId) throws RepositoryException {
        try {
            var hall = getHallDao().getHallById(hallId);
            if( hall == null){
                return null;
            }
            return new HallProxy(hall.getId(), serviceLocator.getService(SeatRepository.class), hall.getNumber());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }


    }

    private HallDao getHallDao() throws RepositoryException {
        if (hallDao == null) {
            try {
                this.hallDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getHallDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return hallDao;
    }

}
